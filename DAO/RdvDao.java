package DAO;

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



public class RdvDao {
    private final Path fichier=Paths.get("Data/Rdv.json");
    private final Gson gson=GsonUtil.creerGson();
    public RdvDao(){
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
    private List<RendezVous> lireFichier(){
        try(Reader reader=Files.newBufferedReader(fichier,StandardCharsets.UTF_8)){
            RendezVous[] tableau =gson.fromJson(reader,RendezVous[].class);
            if(tableau==null){
                return new ArrayList<>();

            }
            return new ArrayList<>(Arrays.asList(tableau));
        }catch(JsonSyntaxException | IOException e){
            List<RendezVous> listeVide=new ArrayList<>();
            ecrireFichier(listeVide);
            return listeVide;
        }

    }
        
    private void ecrireFichier(List<RendezVous> Rdvs){
        try(Writer writer=Files.newBufferedWriter(fichier, StandardCharsets.UTF_8)){
            gson.toJson(Rdvs,writer);
   
        }catch(IOException e){
            throw new RuntimeException("Erreur lors de l'ecriture dans "+fichier,e);
        }
    }
    
     //LES METHODES CRUD:
    public List<RendezVous>findAll(){
        return lireFichier();
    }

    public RendezVous findById(String id){
        if(id==null)
            return null;
        for(RendezVous Rdv:lireFichier()){
            if(id.equals(Rdv.getID_RDV()))
                return Rdv;
        }
        return null;

    }

    public boolean existsById(String id){
        return findById(id)!=null;
    }

    public void save(RendezVous Rdv){
        List<RendezVous> Rdvs=lireFichier();
        for(RendezVous RDV:Rdvs){
            if(RDV.getID_RDV().equals(Rdv.getID_RDV())){
                throw new IllegalArgumentException("le rendez vous ayant l'id  "+Rdv.getID_RDV()+"existe deja");
            }
        }
        Rdvs.add(Rdv);
        ecrireFichier(Rdvs);
    }

    public void update(RendezVous Rdv){
        List<RendezVous> rdvs=lireFichier();
        for(int i=0;i<rdvs.size();i++){
            if(rdvs.get(i).getID_RDV().equals(Rdv.getID_RDV())){
                rdvs.set(i,Rdv);
                ecrireFichier(rdvs);
                return;
            }
        }
        throw new IllegalArgumentException("¨Rendez vous introuvable: "+Rdv.getID_RDV());
    }

    public boolean delete(String id){
        List<RendezVous> rdvs=lireFichier();
        boolean supprime =false;
        for(int i=0;i<rdvs.size();i++){
            if(rdvs.get(i).getID_RDV().equals(id)){
                rdvs.remove(i);
                supprime=true;
                break;
            }
        }
        if(supprime){
            ecrireFichier(rdvs);
        }
        return supprime;
    }

    public long count(){
        return lireFichier().size();
    }


}


