package Entity;

import java.util.List;
public class FichePatient {

    private int idPatient;
    private String nom;
    private String prenom;
    private String telephone;

    private int nombreRDV;
    private int nombreFactures;

    private double totalFacture;
    private double totalAssurance;
    private double totalPatient;
    private double resteAPayer;

    private List<FactureMedical> factures;

    public FichePatient() {
    }

    // Getters & Setters
    public int getIdPatient() {
        return idPatient;
    }
    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
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
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public int getNombreRDV() {
        return nombreRDV;
    }
    public void setNombreRDV(int nombreRDV) {
        this.nombreRDV = nombreRDV;
    }
    public int getNombreFactures() {
        return nombreFactures;
    }
    public void setNombreFactures(int nombreFactures) {
        this.nombreFactures = nombreFactures;
    }
    public double getTotalFacture() {
        return totalFacture;
    }
    public void setTotalFacture(double totalFacture) {
        this.totalFacture = totalFacture;
    }
    public double getTotalAssurance() {
        return totalAssurance;
    }
    public void setTotalAssurance(double totalAssurance) {
        this.totalAssurance = totalAssurance;
    }
    public double getTotalPatient() {
        return totalPatient;
    }
    public void setTotalPatient(double totalPatient) {
        this.totalPatient = totalPatient;
    }
    public double getResteAPayer() {
        return resteAPayer;
    }
    public void setResteAPayer(double resteAPayer) {
        this.resteAPayer = resteAPayer;
    }
    public List<FactureMedicale> getFactures() {
        return factures;
    }
    public void setFactures(List<FactureMedicale> factures) {
        this.factures = factures;
    }

}
