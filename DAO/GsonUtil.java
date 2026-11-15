package DAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class GsonUtil {
    public static Gson creerGson() {
        return new GsonBuilder()// crée une instance de Gson avec des options de configuration spécifiques
                .setPrettyPrinting()//active l'option d'impression formatée pour rendre le JSON plus lisible
                
                //enregistre un adaptateur personnalisé pour la sérialisation et la désérialisation de LocalDate
                .registerTypeAdapter(LocalDate.class, new TypeAdapter<LocalDate>() {
                    @Override
                    public void write(JsonWriter out, LocalDate value) throws IOException {
                        if (value == null) {
                            out.nullValue();
                        } else {
                            out.value(value.toString());
                        }
                    }

                    @Override
                    public LocalDate read(JsonReader in) throws IOException {
                        if (in.peek() == JsonToken.NULL) {
                            in.nextNull();
                            return null;
                        }
                        return LocalDate.parse(in.nextString());
                    }
                })
                //enregistre un adaptateur personnalisé pour la sérialisation et la désérialisation de LocalTime
                .registerTypeAdapter(LocalTime.class, new TypeAdapter<LocalTime>() {
                    @Override
                    public void write(JsonWriter out, LocalTime value) throws IOException {
                        if (value == null) {
                            out.nullValue();
                        } else {
                            out.value(value.toString());
                        }
                    }

                    @Override
                    public LocalTime read(JsonReader in) throws IOException {
                        if (in.peek() == JsonToken.NULL) {
                            in.nextNull();
                            return null;
                        } else {
                            return LocalTime.parse(in.nextString());
                        }
                    }
                })
                .create();//crée et retourne l'instance de Gson configurée avec les adaptateurs personnalisés
    }
}
