package DAO;
import Entity.Patient;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
    private List<Patient> lireFichier(){
        try(Reader reader=Files.newBufferedReader(fichier,StandardCharsets.UTF_8)){
            //GSON lit le fichier json sous forme de tableau
            Patient[] tableau=gson.fromJson(reader,Patient[].class);
            //si le fichier est vide en retourne une liste vide
            if(tableau==null){
                return new ArrayList<>();
            }
            return new ArrayList(Arrays.asList(tableau));
        }catch(JsonSyntaxException | IOException e){
            //si le fichier est illisible ou corrompue,on repart directment d'une liste vide
            List<Patient> listeVide=new ArrayList<>();
            ecrireFichier(listeVide); //il va ecrire []
            return listeVide;
        }

        }
    private void ecrireFichier(List<Patient> patients){
        Path tempFile=Paths.get(fichier.toString()+".tmp"); // cree un autre fichier temporaire dans le mm endroit mais avec une autre extension .tmp
        try{
            try(Writer writer =Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8)){
                gson.toJson(patients,writer);//convertit toute la liste patients en texte JSON formate et l'inscrit dans le fichier .tmp
            }
            Files.move(tempFile,fichier,StandardCopyOption.REPLACE_EXISTING);//apres l'ecriture complete du .tmp on le deplace pour remplacer l'ancien patient.json
        }catch(IOException e){
            throw new RuntimeException("Erreur lors de l'ecriture dans "+fichier,e);
        }
 
    }
    //LES METHODES CRUD:
    
    public List<Patient>findAll(){
        return lireFichier();
    }
    public Patient findById(String cin){
        if(cin==null)
            return null;
        for(Patient p:lireFichier()){
            if(cin.equals(p.getCIN_Matricule()))
                return p;
        }
        return null;

    }
    public boolean existsById(String cin){
        return findById(cin)!=null;
    }
    public void save(Patient patient){
        List<Patient> patients=lireFichier();
        for(Patient p: patients){
            if(p.getCIN_Matricule().equals(patient.getCIN_Matricule())){
                throw new IllegalArgumentException("un patient avec le CIN "+patient.getCIN_Matricule()+" existe déjà");
            }
        }
        patients.add(patient);//si le patient n'existe pas on l'ajoute dans patients
        ecrireFichier(patients);//on reecrit le fichier patients
    }
    public void update(Patient patient){
        List<Patient> patients=lireFichier();
        for(int i=0;i<patients.size();i++){
            if(patients.get(i).getCIN_Matricule().equals(patient.getCIN_Matricule())){//get(i) pour obtenir le patient à l'index i 
                patients.set(i,patient);
                ecrireFichier(patients);
                return;
            }
        }
        throw new IllegalArgumentException("¨Patient introuvable pour mise a jour : "+patient.getCIN_Matricule());

    }
    public boolean delete(String cin){
        List<Patient> patients=lireFichier();
        boolean supprime =patients.removeIf(p->p.getCIN_Matricule().equals(cin));
        if(supprime)
            ecrireFichier(patients);
        return supprime;


    }

    // Méthode pour compter le nombre de patients dans le fichier JSON
    public long count(){
        return lireFichier().size();
    }

    
}