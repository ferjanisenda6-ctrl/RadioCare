package DAO;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import Entity.SalleRadiologie;

import java.io.IOException;
import java.io.Reader;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SalleDao {

    private final Path fichier =Paths.get( "data/salle.json"); // le chemin du fichier JSON
    private final Gson gson = GsonUtil.creerGson(); // on va créer un JSON personnalisé

    // Constructeur 
    public SalleDao(){
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

    private List<SalleRadiologie> lireFichier(){
        try (Reader reader =Files.newBufferedReader(fichier, StandardCharsets.UTF_8)){
            SalleRadiologie[] tableau = gson.fromJson(reader,SalleRadiologie[].class);
            if (tableau == null){
                return new ArrayList<>();
            }
            return new ArrayList<>(Arrays.asList(tableau));
        } catch (JsonSyntaxException | IOException e) {
            System.err.println("Erreur lors de la lecture du fichier " + fichier + " : " + e.getMessage());
            List<SalleRadiologie> listeVide = new ArrayList<>();
            ecrireFichier(listeVide);
            return listeVide;
        }
    }

    private void ecrireFichier(List<SalleRadiologie> salles){
        try {
            String json = gson.toJson(salles);
            Files.writeString(fichier, json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'écriture dans le fichier " + fichier, e);
        }
    }

    public List<SalleRadiologie> findAll() {
        return lireFichier();
    }

    public SalleRadiologie findById(String id){
        if (id== null){
            return null;
        }
        for (SalleRadiologie m: lireFichier()){
            if (id.equals(m.getId_Salle())){
                return m;
            }
        }
        return null;
    }

    public boolean existsById(String id){
        return findById(id) != null;
    }

    public void save(SalleRadiologie salle){
        List<SalleRadiologie> listeSalle = lireFichier();
        for(SalleRadiologie m: listeSalle){
            if (salle.getId_Salle().equals(m.getId_Salle())){
                throw new RuntimeException("Salle Radiologie avec l'ID " + salle.getId_Salle() + " existe déjà.");
            }
        }
        listeSalle.add(salle);
        ecrireFichier(listeSalle);
    }

    public void update(SalleRadiologie salleRadiologie){
        List<SalleRadiologie> listeSalleRadiologies = lireFichier();
        for (int i = 0; i < listeSalleRadiologies.size(); i++){
            if (listeSalleRadiologies.get(i).getId_Salle().equals(salleRadiologie.getId_Salle())){//get(i) pour obtenir le salle à l'index i 
                listeSalleRadiologies.set(i, salleRadiologie);
                ecrireFichier(listeSalleRadiologies);
                return;
            }
        }
        throw new RuntimeException("Salle radiologie avec l'ID " + salleRadiologie.getId_Salle() + " n'existe pas.");
    }


    public void delete(String id){
        List<SalleRadiologie> listeSalleRadiologies = lireFichier();
        boolean supprime = listeSalleRadiologies.removeIf(m -> m.getId_Salle().equals(id));
        if (supprime){
            ecrireFichier(listeSalleRadiologies);
        } else {
            throw new RuntimeException("La salle radiologie avec l'ID " + id + " n'existe pas.");
        }
    }

    // Méthode pour compter le nombre de salles radiologies dans le fichier JSON
    public long count(){
        return lireFichier().size();
    }

}
