package RadioCare.DAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class JsonDao<T> implements GenericDao<T> {

    private final Path fichier;// Chemin vers le fichier JSON
    private final Type typeListe;// Type de la liste d'entités pour la désérialisation 
    private final Function<T, String> idExtractor;
    private final Gson gson;
    protected List<T> cache; // Cache en mémoire pour éviter de lire le fichier à chaque opération


    public JsonDao(String cheminFichier, Type typeListe, Function<T, String> idExtractor) {
        this.fichier = Paths.get(cheminFichier);
        this.typeListe = typeListe;
        this.idExtractor = idExtractor;
        this.gson = new GsonBuilder() 
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .create();
        this.cache = new ArrayList<>();
        initialiserEtCharger();
    }

    private void initialiserEtCharger() {
        try {
            if (fichier.getParent() != null) {
                Files.createDirectories(fichier.getParent());
            }
            if (!Files.exists(fichier)) {
                Files.writeString(fichier, "[]", StandardCharsets.UTF_8);
                this.cache = new ArrayList<>();
                return;
            }
            try (Reader reader = Files.newBufferedReader(fichier, StandardCharsets.UTF_8)) {
                List<T> donnees = gson.fromJson(reader, typeListe);
                this.cache = (donnees != null) ? donnees : new ArrayList<>();
            }
        } catch (JsonSyntaxException | IOException e) {
            // En cas de fichier corrompu : sauvegarde .bak et redémarrage à vide
            sauvegarderCopieCorrompue();
            this.cache = new ArrayList<>();
            sauvegarderFichier();
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

    protected void sauvegarderFichier() {
        Path tempFile = Paths.get(fichier.toString() + ".tmp");
        try {
            try (Writer writer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8)) {
                gson.toJson(cache, writer);
            }
            Files.move(tempFile, fichier, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'écriture dans " + fichier, e);
        }
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(cache); // Retourne une copie pour protéger le cache
    }

    @Override
    public Optional<T> findById(String id) {
        if (id == null) return Optional.empty();
        return cache.stream()
                .filter(e -> id.equals(idExtractor.apply(e)))
                .findFirst();
    }

    @Override
    public boolean existsById(String id) {
        return findById(id).isPresent();
    }

    @Override
    public void save(T entity) {
        String id = idExtractor.apply(entity);
        if (existsById(id)) {
            throw new IllegalArgumentException("Doublon détecté : l'ID " + id + " existe déjà.");
        }
        cache.add(entity);
        sauvegarderFichier();
    }

    @Override
    public void update(T entity) {
        String id = idExtractor.apply(entity);
        for (int i = 0; i < cache.size(); i++) {
            if (id.equals(idExtractor.apply(cache.get(i)))) {
                cache.set(i, entity);
                sauvegarderFichier();
                return;
            }
        }
        throw new IllegalArgumentException("Impossible de mettre à jour : ID " + id + " introuvable.");
    }

    @Override
    public boolean delete(String id) {
        boolean supprime = cache.removeIf(e -> id.equals(idExtractor.apply(e)));
        if (supprime) {
            sauvegarderFichier();
        }
        return supprime;
    }

    @Override
    public long count() {
        return cache.size();
    }
}