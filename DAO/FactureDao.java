package DAO;

import Entity.FactureMedicale;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FactureDao {
    private final Path fichier=Paths.get("Data/Facture.json");
    private final Gson gson=GsonUtil.creerGson();
    public FactureDao(){
        initialiserFichier();
    }
    public void initialiserFichier(){
         try{
            if(fichier.getParent()!=null){
                Files.createDirectories(fichier.getParent());
            }
            if(!Files.exists(fichier)){
                Files.writeString(fichier,"[]", StandardCharsets.UTF_8);
            }
        }catch(IOException e){
            throw new RuntimeException("Erreur lors de l'initialisation "+fichier,e);
        }

    }
     private List<FactureMedicale> lireFichier(){
        try(Reader reader=Files.newBufferedReader(fichier,StandardCharsets.UTF_8)){
            FactureMedicale[] tableau =gson.fromJson(reader,FactureMedicale[].class);
            if(tableau==null){
                return new ArrayList<>();

            }
            return new ArrayList<>(Arrays.asList(tableau));
        }catch(JsonSyntaxException | IOException e){
            List<FactureMedicale> listeVide=new ArrayList<>();
            ecrireFichier(listeVide);
            return listeVide;
        }
     }
    private void ecrireFichier(List<FactureMedicale> factures){
        try(Writer writer=Files.newBufferedWriter(fichier, StandardCharsets.UTF_8)){
            gson.toJson(factures,writer);
   
        }catch(IOException e){
            throw new RuntimeException("Erreur lors de l'ecriture dans "+fichier,e);
        }
    }
    //LES METHODES CRUD:
    public List<FactureMedicale>findAll(){
        return lireFichier();
    }
    public FactureMedicale findById(String id){
        if(id==null)
            return null;
        for(FactureMedicale facture:lireFichier()){
            if(id.equals(facture.getID_RDV()))
                return facture;
        }
        return null;

    }
    public boolean existsById(String id){
        return findById(id)!=null;
    }
    public void save(FactureMedicale facture){
        List<FactureMedicale> factures=lireFichier();
        for(FactureMedicale Facture :factures){
            if(Facture.getNumFacture()==facture.getNumFacture()){
                throw new IllegalArgumentException("le facture de num "+facture.getNumFacture()+"existe deja");
            }
        }
        factures.add(facture);
        ecrireFichier(factures);
    }
    public void update(FactureMedicale facture){
        List<FactureMedicale> factures=lireFichier();
        for(int i=0;i<factures.size();i++){
            if(factures.get(i).getNumFacture()==facture.getNumFacture()){
                factures.set(i,facture);
                ecrireFichier(factures);
                return;
            }
        }
        throw new IllegalArgumentException("¨facture introuvable: "+facture.getNumFacture());

    }
    public boolean delete(String id){
        List<FactureMedicale> factureMedicales=lireFichier();
        boolean supprime =false;
        for(int i=0;i<factureMedicales.size();i++){
            if(factureMedicales.get(i).getID_RDV()==id){
                factureMedicales.remove(i);
                supprime=true;
                break;
            }
        }
        if(supprime){
            ecrireFichier(factureMedicales);
        }
        return supprime;
    }

    public long count(){
        return lireFichier().size();
    }

}
