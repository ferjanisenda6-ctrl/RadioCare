package Entity;

import java.util.List;
public class FichePatient {

    private String CIN ;
    private String nom;
    private String prenom;
    private String telephone;


    
    private int nombreRDV;
    private int nombreFactures;

    private double totalFacture;
    private double totalAssurance;
    private double totalPatient;
    private double resteAPayer;

    private List<FactureMedicale> factures;
    private List<Examen> historiqueExamensIDs;

    //constructeur 
    public FichePatient(String CIN, String nom, String prenom, String telephone, int nombreRDV, int nombreFactures,
            double totalFacture, double totalAssurance, double totalPatient, double resteAPayer,
            List<FactureMedicale> factures, List<Examen> historiqueExamensIDs) {
        this.CIN = CIN;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.nombreRDV = nombreRDV;
        this.nombreFactures = nombreFactures;
        this.totalFacture = totalFacture;
        this.totalAssurance = totalAssurance;
        this.totalPatient = totalPatient;
        this.resteAPayer = resteAPayer;
        this.factures = factures;
        this.historiqueExamensIDs = historiqueExamensIDs;
    }

    // default constructor
    public FichePatient() {
    }
    

    // Getters & Setters
    public String getCIN() {
        return CIN;
    }
    public void setCIN(String idPatient) {
        this.CIN = idPatient;
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
    public List<Examen> getHistoriqueExamensIDs() {
        return historiqueExamensIDs;
    }
    public void setHistoriqueExamensIDs(List<Examen> historiqueExamensIDs) {
        this.historiqueExamensIDs = historiqueExamensIDs;
    }

    // override toString method for better representation
    @Override
    public String toString() {
        return "FichePatient{" +
                "CIN=" + CIN +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", nombreRDV=" + nombreRDV +
                ", nombreFactures=" + nombreFactures +
                ", totalFacture=" + totalFacture +
                ", totalAssurance=" + totalAssurance +
                ", totalPatient=" + totalPatient +
                ", resteAPayer=" + resteAPayer +
                ", factures=" + factures +
                ", historiqueExamensIDs=" + historiqueExamensIDs +
                '}';
    }
    // Override equals and hashCode methods for proper comparison and hashing
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FichePatient other = (FichePatient) obj;
        return CIN == other.CIN;
    }
    @Override
    public int hashCode() {
        return CIN.hashCode();
    }

}
