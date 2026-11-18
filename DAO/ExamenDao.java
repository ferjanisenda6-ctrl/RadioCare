package DAO;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import Entity.Examen;

import java.io.IOException;
import java.io.Reader;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ExamenDao {

    private final Path fichier = Paths.get("data/examen.json");//le chemin du fichier json
    private final Gson gson = GsonUtil.creerGson();//on va creer un json personalisee

    // Constructeur
    public ExamenDao() {
        initialiserFichier();
    }

    // Méthode pour initialiser le fichier JSON
    private void initialiserFichier(){
        try{
            if(fichier.getParent()!= null){
                Files.createDirectories(fichier.getParent());
            }
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'initialisation de " + fichier, e);
        }
    }

    // Méthode pour lire le fichier JSON et retourner une liste d'examens
    private List<Examen> lireFichier(){
        try (Reader reader = Files.newBufferedReader(fichier, StandardCharsets.UTF_8)){
            Examen[] tableau = gson.fromJson(reader, Examen[].class);
            if (tableau == null){
                return new ArrayList<>();
            }
            return new ArrayList<>(Arrays.asList(tableau));
        } catch (JsonSyntaxException | IOException e) {
            System.err.println("Erreur lors de la lecture du fichier " + fichier + " : " + e.getMessage());
            List<Examen> listeVide = new ArrayList<>();
            ecrireFichier(listeVide);
            return listeVide;
        }
    }

    // Méthode pour écrire une liste d'examens dans le fichier JSON
    private void ecrireFichier(List<Examen> examens){
        try{
            Files.newBufferedWriter(fichier, StandardCharsets.UTF_8);
        }catch (IOException e){
            throw new RuntimeException("Erreur dans l'écriture du fichier " + fichier,e);
        }
    }


    //LES METHODES CRUD:

    // Méthode pour ajouter un examen à la liste et l'écrire dans le fichier JSON
    public void save(Examen examen) {
        List<Examen> examens = lireFichier();
        examens.add(examen);
        ecrireFichier(examens);
    }

    // Méthode pour récupérer tous les examens depuis le fichier JSON
    public List<Examen> findAll() {
        return lireFichier();
    }

    //Méthode pour récupérer un examen par son ID depuis le fichier Json
    public Examen findById(String id){
        for(Examen examen : lireFichier()){
            if(examen.getId().equals(id)){
                return examen;
            }
        }
        return null;
    }

    // Méthode pour vérifier si un examen existe par son ID
    public boolean existsById(String id){
        return findById(id) != null;
    }

    // Méthode pour mettre à jour un examen existant dans le fichier JSON
    public void update(Examen examen) {
        List<Examen> examens = lireFichier();
        for (int i = 0; i < examens.size(); i++) {
            if (examens.get(i).getId().equals(examen.getId())) {//get(i) pour obtenir l'examen à l'index i 
                examens.set(i, examen);
                ecrireFichier(examens);
                return;
            }
        }
        throw new IllegalArgumentException("Aucun examen avec l'ID " + examen.getId() + " trouvé pour la mise à jour.");
    }

    // Méthode pour supprimer un examen par son ID depuis le fichier JSON
    public boolean delete(String id){
        List<Examen> examens=lireFichier();
        boolean supprime =false;
        for(int i=0;i<examens.size();i++){
            if(examens.get(i).getID_Examen().equals(id)){
                examens.remove(i);
                supprime=true;
                break;
            }
        }
        if(supprime){
            ecrireFichier(examens);
        }
        return supprime;
    }

    // Méthode pour compter le nombre d'examens dans le fichier JSON
    public long count() {
        return lireFichier().size();
    }

}
