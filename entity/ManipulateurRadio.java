package Entity;

import Entity.enums.Habilitations;

public class ManipulateurRadio {
    private String ID_manip;
    private String CIN,nom,prenom;
    private Habilitations habilitations;
    private double TarifHoraire;
    private boolean StatutDispo;
    public ManipulateurRadio(String iD_manip, String cIN, String nom, String prenom, Habilitations habilitations,
            double tarifHoraire, boolean statutDispo) {
        ID_manip = iD_manip;
        CIN = cIN;
        this.nom = nom;
        this.prenom = prenom;
        this.habilitations = habilitations;
        TarifHoraire = tarifHoraire;
        StatutDispo = statutDispo;
    }
    public String getID_manip() {
        return ID_manip;
    }
    public void setID_manip(String iD_manip) {
        ID_manip = iD_manip;
    }
    public String getCIN() {
        return CIN;
    }
    public void setCIN(String cIN) {
        CIN = cIN;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public Habilitations getHabilitations() {
        return habilitations;
    }
    public void setHabilitations(Habilitations habilitations) {
        this.habilitations = habilitations;
    }
    public double getTarifHoraire() {
        return TarifHoraire;
    }
    public void setTarifHoraire(double tarifHoraire) {
        TarifHoraire = tarifHoraire;
    }
    public boolean isStatutDispo() {
        return StatutDispo;
    }
    public void setStatutDispo(boolean statutDispo) {
        StatutDispo = statutDispo;
    }
    

}
