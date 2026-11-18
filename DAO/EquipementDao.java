package DAO;

import Entity.EquipementImagerie;

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


public class EquipementDao {
    private final Path Fichier=Paths.get("Data/equipement.json");
    private final Gson gson=GsonUtil.creerGson();
    public EquipementDao(){
        initialiserFichier();
    }
    public void initialiserFichier(){
        try{
            if(Fichier.getParent()!=null){
                Files.createDirectories(Fichier.getParent());

            }if(!Files.exists(Fichier)){
                Files.writeString(Fichier,"[]",StandardCharsets.UTF_8);
            }
        }catch(IOException e){
            throw new RuntimeException("erreur lors de l'initialisation de "+Fichier,e);
        }
    }
    private List<EquipementImagerie> lireFichier(){
        try(Reader reader=Files.newBufferedReader(Fichier,StandardCharsets.UTF_8)){
            EquipementImagerie[] tableau =gson.fromJson(reader,EquipementImagerie[].class);
            if(tableau==null){
                return new ArrayList<>();

            }
            return new ArrayList<>(Arrays.asList(tableau));
        }catch(JsonSyntaxException | IOException e){
            List<EquipementImagerie> listeVide=new ArrayList<>();
            ecrireFichier(listeVide);
            return listeVide;
        }

    }
    private void ecrireFichier(List<EquipementImagerie> equipements){
        try(Writer writer=Files.newBufferedWriter(Fichier, StandardCharsets.UTF_8)){
            gson.toJson(equipements,writer);
   
        }catch(IOException e){
            throw new RuntimeException("Erreur lors de l'ecriture dans "+Fichier,e);
        }
    }

     //LES METHODES CRUD:
    public List<EquipementImagerie>findAll(){
        return lireFichier();
    }
    public EquipementImagerie findById(String id){
        if(id==null)
            return null;
        for(EquipementImagerie E:lireFichier()){
            if(id.equals(E.getID_Equipement()))
                return E;
        }
        return null;

    }
    public boolean existsById(String id){
        return findById(id)!=null;
    }
    public void save(EquipementImagerie equipement){
        List<EquipementImagerie> equipements=lireFichier();
        for(EquipementImagerie E:equipements){
            if(E.getID_Equipement().equals(equipement.getID_Equipement())){
                throw new IllegalArgumentException("l'equipement ayant l'id  "+equipement.getID_Equipement()+" existe déjà");
            }
        }
        equipements.add(equipement);
        ecrireFichier(equipements);
    }
    public void update(EquipementImagerie equipement){
        List<EquipementImagerie> equipements=lireFichier();
        for(int i=0;i<equipements.size();i++){
            if(equipements.get(i).getID_Equipement().equals(equipement.getID_Equipement())){
                equipements.set(i,equipement);
                ecrireFichier(equipements);
                return;
            }
        }
        throw new IllegalArgumentException("¨Equipement introuvable: "+equipement.getID_Equipement());
    }


    public boolean delete(String id){
        List<EquipementImagerie> equipementImageries=lireFichier();
        boolean supprime =false;
        for(int i=0;i<equipementImageries.size();i++){
            if(equipementImageries.get(i).getID_Equipement().equals(id)){
                equipementImageries.remove(i);
                supprime=true;
                break;
            }
        }
        if(supprime){
            ecrireFichier(equipementImageries);
        }
        return supprime;
    }


}
