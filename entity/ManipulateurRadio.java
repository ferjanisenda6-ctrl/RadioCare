package Entity;

import java.util.Objects;

import Entity.enums.Habilitations;

public class ManipulateurRadio {
    private String ID_manip;
    private int CIN;
    private String nom,prenom;
    private Habilitations habilitations;
    private double TarifHoraire;
    private boolean StatutDispo;

    public ManipulateurRadio(){} //construteur vide : pour JSON

    
    public ManipulateurRadio(String iD_manip, int CIN, String nom, String prenom, Habilitations habilitations,
            double tarifHoraire, boolean statutDispo) {
        ID_manip = iD_manip;
        CIN = CIN;
        this.nom = nom;
        this.prenom = prenom;
        this.habilitations = habilitations;
        TarifHoraire = tarifHoraire;
        StatutDispo = statutDispo;
    }//constructeur paramétré pour le code


    // Getters and Setters
    public String getID_manip() {
        return ID_manip;
    }
    public void setID_manip(String iD_manip) {
        ID_manip = iD_manip;
    }
    public int getCIN() {
        return CIN;
    }
    public void setCIN(int cIN) {
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

    // Override toString method for better representation
    @Override
    public String toString() {
        return "ManipulateurRadio [ID_manip=" + ID_manip + ", CIN=" + CIN + ", nom=" + nom + ", prenom=" + prenom
                + ", habilitations=" + habilitations + ", TarifHoraire=" + TarifHoraire + ", StatutDispo=" + StatutDispo
                + ", getID_manip()=" + getID_manip() + ", getCIN()=" + getCIN() + ", getNom()=" + getNom()
                + ", getPrenom()=" + getPrenom() + ", getHabilitations()=" + getHabilitations() + ", getTarifHoraire()="
                + getTarifHoraire() + ", isStatutDispo()=" + isStatutDispo() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }
  
    // Override equals and hashCode methods for proper comparison and hashing
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ManipulateurRadio other = (ManipulateurRadio) obj;
        return Objects.equals(CIN, other.CIN);
    }


    @Override
    public int hashCode() {
        return Objects.hash(CIN); 
  
    }
   
    
    

}
