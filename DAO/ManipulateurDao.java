package DAO;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import Entity.ManipulateurRadio;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ManipulateurDao {
    
    private final Path fichier = Paths.get("data/manipulateur.json");//le chemin du fichier json
    private final Gson gson = GsonUtil.creerGson();//on va creer un json personalisee   

    // Constructeur 
    public ManipulateurDao() {
        initialiserFichier();
    }


    private void initialiserFichier(){
        try{
            if(fichier.getParent()!=null){
                Files.createDirectories(fichier.getParent());
            }
            if (!Files.exists(fichier)){
                Files.writeString(fichier, "[]", StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'initialisation de " + fichier, e);
        }
    }

    private List<ManipulateurRadio> lireFichier(){
        try (Reader reader =Files.newBufferedReader(fichier, StandardCharsets.UTF_8)){
            ManipulateurRadio[] tableau = gson.fromJson(reader,ManipulateurRadio[].class);
            if (tableau == null){
                return new ArrayList<>();
            }
            return new ArrayList<>(Arrays.asList(tableau));
        } catch (JsonSyntaxException | IOException e) {
            System.err.println("Erreur lors de la lecture du fichier " + fichier + " : " + e.getMessage());
            List<ManipulateurRadio> listeVide = new ArrayList<>();
            ecrireFichier(listeVide);
            return listeVide;
        }
    }

    private void ecrireFichier(List<ManipulateurRadio> liste){
        try {
            String json = gson.toJson(liste);
            Files.writeString(fichier, json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'écriture dans le fichier " + fichier, e);
        }
    }

    //CRUD operations for ManipulateurRadio can be added here, similar to PatientDao and MedecinDao

    public List<ManipulateurRadio> findAll() {
        return lireFichier();
    }

    public ManipulateurRadio findById(String id){
        if (id== null){
            return null;
        }
        for (ManipulateurRadio m: lireFichier()){
            if (id.equals(m.getID_manip())){
                return m;
            }
        }
        return null;
    }

    public boolean existsById(String id){
        return findById(id) != null;
    }

    public void save(ManipulateurRadio manipulateur){
        List<ManipulateurRadio> listeManip = lireFichier();
        for(ManipulateurRadio m: listeManip){
            if (manipulateur.getID_manip().equals(m.getID_manip())){
                throw new RuntimeException("Manipulateur avec l'ID " + manipulateur.getID_manip() + " existe déjà.");
            }
        }
        listeManip.add(manipulateur);
        ecrireFichier(listeManip);
    }

    public void update(ManipulateurRadio manipulateur){
        List<ManipulateurRadio> listeManip = lireFichier();
        for (int i = 0; i < listeManip.size(); i++){
            if (listeManip.get(i).getID_manip().equals(manipulateur.getID_manip())){//get(i) pour obtenir le manipulateur à l'index i 
                listeManip.set(i, manipulateur);
                ecrireFichier(listeManip);
                return;
            }
        }
        throw new RuntimeException("Manipulateur avec l'ID " + manipulateur.getID_manip() + " n'existe pas.");
    }
    public void delete(String id){
        List<ManipulateurRadio> listeManip = lireFichier();
        boolean supprime = listeManip.removeIf(m -> m.getID_manip().equals(id));
        if (supprime){
            ecrireFichier(listeManip);
        } else {
            throw new RuntimeException("Manipulateur avec l'ID " + id + " n'existe pas.");
        }
    }

    // Méthode pour compter le nombre de manipulateurs dans le fichier JSON
    public long count(){
        return lireFichier().size();
    }   

}

