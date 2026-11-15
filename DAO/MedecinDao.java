package DAO;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import  Entiy.MedecinRadiologueEntity;
import radiocare.entity.Specialite;

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

    private final Path fichier = Paths.get("data/medecins.json");
    private final Gson gson = GsonUtil.creerGson();

    public MedecinDao() {
        initialiserFichier();
    }

    // --- GESTION DU FICHIER JSON (Sécurisée) ---

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

    private List<MedecinRadiologue> lireFichier() {
        try (Reader reader = Files.newBufferedReader(fichier, StandardCharsets.UTF_8)) {
            MedecinRadiologue[] tableau = gson.fromJson(reader, MedecinRadiologue[].class);
            if (tableau == null) {
                return new ArrayList<>();
            }
            return new ArrayList<>(Arrays.asList(tableau));
        } catch (JsonSyntaxException | IOException e) {
            sauvegarderCopieCorrompue();
            List<MedecinRadiologue> listeVide = new ArrayList<>();
            ecrireFichier(listeVide);
            return listeVide;
        }
    }

    private void ecrireFichier(List<MedecinRadiologue> medecins) {
        Path tempFile = Paths.get(fichier.toString() + ".tmp");
        try {
            try (Writer writer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8)) {
                gson.toJson(medecins, writer);
            }
            Files.move(tempFile, fichier, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'écriture dans " + fichier, e);
        }
    }

    private void sauvegarderCopieCorrompue() {
        try {
            if (Files.exists(fichier)) {
                Path backup = Paths.get(fichier.toString() + ".bak");
                Files.copy(fichier, backup, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException ex) {
            System.err.println("Impossible de créer le backup .bak : " + ex.getMessage());
        }
    }

    // --- OPÉRATIONS CRUD DE BASE ---

    public List<MedecinRadiologue> findAll() {
        return lireFichier();
    }

    public MedecinRadiologue findById(String id) {
        if (id == null) {
            return null;
        }
        for (MedecinRadiologue m : lireFichier()) {
            if (id.equals(m.getIdMedecin())) {
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
            if (m.getIdMedecin().equals(medecin.getIdMedecin())) {
                throw new IllegalArgumentException("Un médecin avec l'ID " + medecin.getIdMedecin() + " existe déjà.");
            }
        }
        medecins.add(medecin);
        ecrireFichier(medecins);
    }

    public void update(MedecinRadiologue medecin) {
        List<MedecinRadiologue> medecins = lireFichier();
        for (int i = 0; i < medecins.size(); i++) {
            if (medecins.get(i).getIdMedecin().equals(medecin.getIdMedecin())) {
                medecins.set(i, medecin);
                ecrireFichier(medecins);
                return;
            }
        }
        throw new IllegalArgumentException("Médecin introuvable pour mise à jour : " + medecin.getIdMedecin());
    }

    public boolean delete(String id) {
        List<MedecinRadiologue> medecins = lireFichier();
        boolean supprime = medecins.removeIf(m -> m.getIdMedecin().equals(id));
        if (supprime) {
            ecrireFichier(medecins);
        }
        return supprime;
    }

    public long count() {
        return lireFichier().size();
    }

    // --- RECHERCHES SPÉCIFIQUES ---

    public Optional<MedecinRadiologue> findByCin(String cin) {
        if (cin == null) return Optional.empty();
        return lireFichier().stream()
                .filter(m -> cin.equals(m.getCin()))
                .findFirst();
    }

    public List<MedecinRadiologue> findBySpecialite(Specialite specialite) {
        if (specialite == null) return new ArrayList<>();
        return lireFichier().stream()
                .filter(m -> m.getSpecialite() == specialite)
                .collect(Collectors.toList());
    }

    public List<MedecinRadiologue> searchByNomOuPrenom(String motCle) {
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