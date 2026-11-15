package DAO;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class PatientDao {
    private final Path fichier=Paths.get("Data/patient.json");//le chemin du fichier json
    private final Gson gson=GsonUtil.creerGson();//l'instance de Gson pour la sérialisation et désérialisation JSON
    

}