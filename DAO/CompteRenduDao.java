package DAO;
import Entity.CompteRenduMedical;
import Entity.RendezVous;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class CompteRenduDao {
    private final Path fichier=Paths.get("Data/CompteRendu.json");
    private final Gson gson=GsonUtil.creerGson();
    public CompteRenduDao(){
        initialiserFichier();
    }
    public void initialiserFichier(){
         try{
            if(fichier.getParent()!=null){
                Files.createDirectories(fichier.getParent());
            }
            if(!Files.exists(fichier)){
                Files.writeString(fichier,"[]", StandardCharsets.UTF_8);
            }
        }catch(IOException e){
            throw new RuntimeException("Erreur lors de l'initialisation "+fichier,e);
        }
    }

    private List<CompteRenduMedical> lireFichier(){
        try(Reader reader=Files.newBufferedReader(fichier,StandardCharsets.UTF_8)){
            CompteRenduMedical[] tableau =gson.fromJson(reader,CompteRenduMedical[].class);
            if(tableau==null){
                return new ArrayList<>();

            }
            return new ArrayList<>(Arrays.asList(tableau));
        }catch(JsonSyntaxException | IOException e){
            List<CompteRenduMedical> listeVide=new ArrayList<>();
            ecrireFichier(listeVide);
            return listeVide;
        }

    }
     private void ecrireFichier(List<CompteRenduMedical> CompteRendu){
        try(Writer writer=Files.newBufferedWriter(fichier, StandardCharsets.UTF_8)){
            gson.toJson(CompteRendu,writer);
   
        }catch(IOException e){
            throw new RuntimeException("Erreur lors de l'ecriture dans "+fichier,e);
        }
    }
      //LES METHODES CRUD:
    public List<CompteRenduMedical>findAll(){
        return lireFichier();
    }
    public CompteRenduMedical findById(String id){
        if(id==null)
            return null;
        for(CompteRenduMedical CompteRendu:lireFichier()){
            if(id.equals(CompteRendu.getID_CompteRenduMedical()))
                return CompteRendu;
        }
        return null;

    }
    public boolean existsById(String id){
        return findById(id)!=null;
    }
    public void save(CompteRenduMedical CompteRendu){
        List<CompteRenduMedical> ComptesRendu=lireFichier();
        for(CompteRenduMedical CpteRendu:ComptesRendu){
            if(CpteRendu.getID_CompteRenduMedical().equals(CompteRendu.getID_CompteRenduMedical())){
                throw new IllegalArgumentException("le rendez vous ayant l'id  "+CompteRendu.getID_CompteRenduMedical()+"existe deja");
        }
        ComptesRendu.add(CompteRendu);
        ecrireFichier(ComptesRendu);}
    }
    public void update(CompteRenduMedical cpteRendu){
        List<CompteRenduMedical> compteRenduMedicals=lireFichier();
        for(int i=0;i<compteRenduMedicals.size();i++){
            if(compteRenduMedicals.get(i).getID_CompteRenduMedical().equals(cpteRendu.getID_CompteRenduMedical())){
                compteRenduMedicals.set(i,cpteRendu);
                ecrireFichier(compteRenduMedicals);
                return;
            }
        }
        throw new IllegalArgumentException("CompteRendu introuvable: "+cpteRendu.getID_CompteRenduMedical());

    }
    public boolean delete(String id){
        List<CompteRenduMedical> compteRenduMedicals=lireFichier();
        boolean supprime =false;
        for(int i=0;i<compteRenduMedicals.size();i++){
            if(compteRenduMedicals.get(i).getID_CompteRenduMedical().equals(id)){
                compteRenduMedicals.remove(i);
                supprime=true;
                break;
            }
        }
        if(supprime){
            ecrireFichier(compteRenduMedicals);
        }
        return supprime;
    }

    public long count(){
        return lireFichier().size();
    }

    


}
