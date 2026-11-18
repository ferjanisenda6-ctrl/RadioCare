package DAO;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


import Entity.MedecinRadiologue;


import java.io.IOException;
import java.io.Reader;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MedecinDao {

    private final Path fichier = Paths.get("data/medecin.json");//le chemin du fichier json
    private final Gson gson = GsonUtil.creerGson();//on va creer un json personalisee
    
    // Constructeur
    public MedecinDao() {
        initialiserFichier();
    }


    // Méthode pour initialiser le fichier JSON
    private void initialiserFichier() {
        try {
            if (fichier.getParent() != null) {
                Files.createDirectories(fichier.getParent());
            }
            if (!Files.exists(fichier)) {
                Files.writeString(fichier, "[]", StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'initialisation de " + fichier, e);
        }
    }

    // Méthode pour lire le fichier JSON et retourner une liste de médecins
    private List<MedecinRadiologue> lireFichier() {
        try (Reader reader = Files.newBufferedReader(fichier, StandardCharsets.UTF_8)) {
            MedecinRadiologue[] tableau = gson.fromJson(reader, MedecinRadiologue[].class);
            if (tableau == null) {
                return new ArrayList<>();
            }
            return new ArrayList<>(Arrays.asList(tableau));
        } catch (JsonSyntaxException | IOException e) {
            System.err.println("Erreur lors de la lecture du fichier " + fichier + " : " + e.getMessage());
            List<MedecinRadiologue> listeVide = new ArrayList<>();
            ecrireFichier(listeVide);
            return listeVide;
        }
    }

    // Méthode pour écrire une liste de médecins dans le fichier JSON
    private void ecrireFichier(List<MedecinRadiologue> medecins) {
        try {
            String json = gson.toJson(medecins);
            Files.writeString(fichier, json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'écriture dans le fichier " + fichier, e);
        }
    }

    // Méthodes CRUD et autres opérations

    public List<MedecinRadiologue> findAll() {
        return lireFichier();
    }

    public MedecinRadiologue findById(String id) {
        if (id == null) {
            return null;
        }
        for (MedecinRadiologue m : lireFichier()) {
            if (id.equals(m.getID_Medecin())) {
                return m;
            }
        }
        return null;
    }

    public boolean existsById(String id) {
        return findById(id) != null;
    }

    public void save(MedecinRadiologue medecin) {
        List<MedecinRadiologue> medecins = lireFichier();
        for (MedecinRadiologue m : medecins) {
            if (m.getID_Medecin().equals(medecin.getID_Medecin())) {
                throw new IllegalArgumentException("Un médecin avec l'ID " + medecin.getID_Medecin() + " existe déjà.");
            }
        }
        medecins.add(medecin);
        ecrireFichier(medecins);
    }

    public void update(MedecinRadiologue medecin) {
        List<MedecinRadiologue> medecins = lireFichier();
        for (int i = 0; i < medecins.size(); i++) {
            if (medecins.get(i).getID_Medecin().equals(medecin.getID_Medecin())) {//get(i) pour obtenir le médecin à l'index i
                medecins.set(i, medecin);
                ecrireFichier(medecins);
                return;
            }
        }
        throw new IllegalArgumentException("Médecin introuvable pour mise à jour : " + medecin.getID_Medecin());
    }

    public boolean delete(String id){
        List<MedecinRadiologue> medecinRadiologues=lireFichier();
        boolean supprime =false;
        for(int i=0;i<medecinRadiologues.size();i++){
            if(medecinRadiologues.get(i).getID_Medecin().equals(id)){
                medecinRadiologues.remove(i);
                supprime=true;
                break;
            }
        }
        if(supprime){
            ecrireFichier(medecinRadiologues);
        }
        return supprime;
    }

    // Méthode pour compter le nombre de médecins dans le fichier JSON
    public long count() {
        return lireFichier().size();
    }}