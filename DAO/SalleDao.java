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

    private final String fichier = "data/salle.json"; // le chemin du fichier JSON
    private final Gson gson = GsonUtil.creerGson(); // on va créer un JSON personnalisé

    // Constructeur 
    public SalleDao(){}//
    

}
