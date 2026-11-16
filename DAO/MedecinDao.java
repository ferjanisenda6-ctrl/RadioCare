package DAO;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import Entity.MedecinRadiologueEntity;
import Entity.enums.Specialite;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MedecinDao {

    private final Path fichier = Paths.get("data/medecins.json");//le chemin du fichier json
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
    private List<MedecinRadiologueEntity> lireFichier() {
        try (Reader reader = Files.newBufferedReader(fichier, StandardCharsets.UTF_8)) {
            MedecinRadiologueEntity[] tableau = gson.fromJson(reader, MedecinRadiologueEntity[].class);
            if (tableau == null) {
                return new ArrayList<>();
            }
            return new ArrayList<>(Arrays.asList(tableau));
        } catch (JsonSyntaxException | IOException e) {
            System.err.println("Erreur lors de la lecture du fichier " + fichier + " : " + e.getMessage());
            List<MedecinRadiologueEntity> listeVide = new ArrayList<>();
            ecrireFichier(listeVide);
            return listeVide;
        }
    }

    // Méthode pour écrire une liste de médecins dans le fichier JSON
    private void ecrireFichier(List<MedecinRadiologueEntity> medecins) {
        try {
            String json = gson.toJson(medecins);
            Files.writeString(fichier, json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'écriture dans le fichier " + fichier, e);
        }
    }

    // Méthodes CRUD et autres opérations

    public List<MedecinRadiologueEntity> findAll() {
        return lireFichier();
    }

    public MedecinRadiologueEntity findById(String id) {
        if (id == null) {
            return null;
        }
        for (MedecinRadiologueEntity m : lireFichier()) {
            if (id.equals(m.getID_Medecin())) {
                return m;
            }
        }
        return null;
    }

    public boolean existsById(String id) {
        return findById(id) != null;
    }

    public void save(MedecinRadiologueEntity medecin) {
        List<MedecinRadiologueEntity> medecins = lireFichier();
        for (MedecinRadiologueEntity m : medecins) {
            if (m.getID_Medecin().equals(medecin.getID_Medecin())) {
                throw new IllegalArgumentException("Un médecin avec l'ID " + medecin.getID_Medecin() + " existe déjà.");
            }
        }
        medecins.add(medecin);
        ecrireFichier(medecins);
    }

    public void update(MedecinRadiologueEntity medecin) {
        List<MedecinRadiologueEntity> medecins = lireFichier();
        for (int i = 0; i < medecins.size(); i++) {
            if (medecins.get(i).getID_Medecin().equals(medecin.getID_Medecin())) {
                medecins.set(i, medecin);
                ecrireFichier(medecins);
                return;
            }
        }
        throw new IllegalArgumentException("Médecin introuvable pour mise à jour : " + medecin.getID_Medecin());
    }

    public boolean delete(String id) {
        List<MedecinRadiologueEntity> medecins = lireFichier();
        boolean supprime = medecins.removeIf(m -> m.getID_Medecin().equals(id));
        if (supprime) {
            ecrireFichier(medecins);
        }
        return supprime;
    }

    public long count() {
        return lireFichier().size();
    }


    // Méthodes de recherche 
    public Optional<MedecinRadiologueEntity> findByCin(String cin) {
        if (cin == null) return Optional.empty();
        return lireFichier().stream()
                .filter(m -> cin.equals(m.getCIN()))
                .findFirst();
    }

    // Méthode pour trouver les médecins par spécialité
    public List<MedecinRadiologueEntity> findBySpecialite(Specialite specialite) {
        if (specialite == null) return new ArrayList<>();
        return lireFichier().stream()
                .filter(m -> m.getSpecialite() == specialite)
                .collect(Collectors.toList());
    }

    // Méthode pour rechercher les médecins par nom ou prénom
    public List<MedecinRadiologueEntity> searchByNomOuPrenom(String motCle) {
        if (motCle == null || motCle.trim().isEmpty()) {
            return findAll();
        }
        String filtre = motCle.toLowerCase();
        return lireFichier().stream()
                .filter(m -> m.getNom().toLowerCase().contains(filtre) || 
                             m.getPrenom().toLowerCase().contains(filtre))
                .collect(Collectors.toList());
    }
}