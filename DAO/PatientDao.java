package DAO;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import Entity.PatientEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PatientDao {
    private final Path fichier=Paths.get("Data/patient.json");//le chemin du fichier json
    private final Gson gson=GsonUtil.creerGson(); //on va creer un json personalisee
    public PatientDao(){
        initialiserFichier();
    }
    public void initialiserFichier(){
        try{
            if(fichier.getParent()!=null){ //on verifie l'existence du fichier parent du fichier json (data dans notre cas)
                Files.createDirectories(fichier.getParent()); 
            }
            if(!Files.exists(fichier)){ //on verifie si le fichier.json est absent on le cree
                Files.writeString(fichier, "[]", StandardCharsets.UTF_8); //on l'inisialise [] c-a-d il n'ya aucun patient dans le fichier                                
            }

        }catch(IOException e){ //(l'exception d'entree/sortie)
            throw new RuntimeException("erreur lors de l'initialisation de "+fichier,e) ;
        }
    }
    private List<PatientEntity> lireFichier(){
        try(Reader reader=Files.newBufferedReader(fichier,StandardCharsets.UTF_8)){
            //GSON lit le fichier json sous forme de tableau
            PatientEntity[] tableau=gson.fromJson(reader,PatientEntity[].class);
            //si le fichier est vide en retourne une liste vide
            if(tableau==null){
                return new ArrayList<>();
            }
            return new ArrayList(Arrays.asList(tableau));
        }catch(JsonSyntaxException | IOException e){
            //si le fichier est illisible ou corrompue,on repart directment d'une liste vide
            List<PatientEntity> listeVide=new ArrayList<>();
            ecrireFichier(listeVide); //il va ecrire []
            return listeVide;

        }

  
    }

}