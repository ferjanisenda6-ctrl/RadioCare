package DAO;
import Entity.Paiement;

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


public class PaiementDao {
    private final Path fichier=Paths.get("Data/Paiement.json");
    private final Gson gson=GsonUtil.creerGson();

    public PaiementDao(){
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
    private List<Paiement> lireFichier(){
        try(Reader reader=Files.newBufferedReader(fichier,StandardCharsets.UTF_8)){
            Paiement[] tableau =gson.fromJson(reader,Paiement[].class);
            if(tableau==null){
                return new ArrayList<>();

            }
            return new ArrayList<>(Arrays.asList(tableau));
        }catch(JsonSyntaxException | IOException e){
            List<Paiement> listeVide=new ArrayList<>();
            ecrireFichier(listeVide);
            return listeVide;
        }

    }
    private void ecrireFichier(List<Paiement> paiements){
        try(Writer writer=Files.newBufferedWriter(fichier, StandardCharsets.UTF_8)){
            gson.toJson(paiements,writer);
   
        }catch(IOException e){
            throw new RuntimeException("Erreur lors de l'ecriture dans "+fichier,e);
        }
    }
    //LES METHODES CRUD:
    public List<Paiement>findAll(){
        return lireFichier();
    }
    public Paiement findById(String id){
        if(id==null)
            return null;
        for(Paiement paiement:lireFichier()){
            if(id.equals(paiement.getID_Paiement()))
                return paiement;
        }
        return null;

    }
    public boolean existsById(String id){
        return findById(id)!=null;
    }
    public void save(Paiement paiement){
        List<Paiement> paiements=lireFichier();
        for(Paiement paiement2:paiements){
            if(paiement2.getID_Paiement().equals(paiement.getID_Paiement())){
                throw new IllegalArgumentException("le paiement ayant l'id  "+paiement.getID_Paiement()+"existe deja");
            }
        }
        paiements.add(paiement);
        ecrireFichier(paiements);
    }
    public void update(Paiement paiement){
        List<Paiement> paiements=lireFichier();
        for(int i=0;i<paiements.size();i++){
            if(paiements.get(i).getID_Paiement().equals(paiement.getID_Paiement())){
                paiements.set(i,paiement);
                ecrireFichier(paiements);
                return;
            }
        }
        throw new IllegalArgumentException("¨Rendez vous introuvable: "+paiement.getID_Paiement());

    }
    public boolean delete(String id){
        List<Paiement> paiements=lireFichier();
        boolean supprime =false;
        for(int i=0;i<paiements.size();i++){
            if(paiements.get(i).getID_Paiement().equals(id)){
                paiements.remove(i);
                supprime=true;
                break;
            }
        }
        if(supprime){
            ecrireFichier(paiements);
        }
        return supprime;
    }

    public long count(){
        return lireFichier().size();
    }



    
    


}
