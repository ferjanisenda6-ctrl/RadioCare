package DAO;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import  Entity.MedecinRadiologueEntity;


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

    private static final String FILE_PATH = "src/main/resources/medecins.json";// Path to the JSON file
    private static final Gson gson = new Gson();// Gson instance for JSON serialization/deserialization

    // Method to retrieve all MedecinRadiologueEntity objects from the JSON file
    public List<MedecinRadiologueEntity> getAllMedecins() {
        try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH), StandardCharsets.UTF_8)) {
            MedecinRadiologueEntity[] medecinsArray = gson.fromJson(reader, MedecinRadiologueEntity[].class);
            return medecinsArray != null ? Arrays.asList(medecinsArray) : new ArrayList<>();
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Method to retrieve a MedecinRadiologueEntity object by its ID from the JSON file
    public Optional<MedecinRadiologueEntity> getMedecinById(int id) {
        return getAllMedecins().stream()
                .filter(medecin -> String.valueOf(medecin.getID_Medecin()).equals(String.valueOf(id)))
                .findFirst();
    }

    // Method to add a new MedecinRadiologueEntity object to the JSON file
    public void addMedecin(MedecinRadiologueEntity newMedecin) {
        List<MedecinRadiologueEntity> medecins = getAllMedecins();
        medecins.add(newMedecin);
        saveAllMedecins(medecins);
    }

    // Method to update an existing MedecinRadiologueEntity object in the JSON file
    public void updateMedecin(MedecinRadiologueEntity updatedMedecin) {
        List<MedecinRadiologueEntity> medecins = getAllMedecins();
        for (int i = 0; i < medecins.size(); i++) {
            if (String.valueOf(medecins.get(i).getID_Medecin()).equals(String.valueOf(updatedMedecin.getID_Medecin()))) {
                medecins.set(i, updatedMedecin);
                break;
            }
        }
        saveAllMedecins(medecins);
    }

    // Method to delete a MedecinRadiologueEntity object from the JSON file
    public void deleteMedecin(int id) {
        List<MedecinRadiologueEntity> medecins = getAllMedecins();
        medecins = medecins.stream()
                .filter(medecin -> !String.valueOf(medecin.getID_Medecin()).equals(String.valueOf(id)))
                .collect(Collectors.toList());
        saveAllMedecins(medecins);
    }

    // Private method to save the list of MedecinRadiologueEntity objects back to the JSON file
    private void saveAllMedecins(List<MedecinRadiologueEntity> medecins) {
        Path tempFilePath = Paths.get(FILE_PATH + ".tmp");
        try (Writer writer = Files.newBufferedWriter(tempFilePath, StandardCharsets.UTF_8)) {
            gson.toJson(medecins, writer);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            Files.move(tempFilePath, Paths.get(FILE_PATH), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
