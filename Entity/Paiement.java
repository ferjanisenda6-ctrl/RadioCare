package Entity;

import Entity.enums.ModePaiement;
import java.time.LocalDate;

public class Paiement {
    private String ID_Paiement;
    private int NumFacture;
    private LocalDate DatePaiement;
    private double MontantRecu;
    private ModePaiement modePaiement ;
    private String ReferenceTextuellePreuve;

    // Default constructor
    public Paiement() {
    }
    // Parameterized constructor
    public Paiement(String ID_Paiement, int NumFacture, LocalDate DatePaiement, double MontantRecu, ModePaiement modePaiement, String ReferenceTextuellePreuve) {
        this.ID_Paiement = ID_Paiement;
        this.NumFacture = NumFacture;
        this.DatePaiement = DatePaiement;
        this.MontantRecu = MontantRecu;
        this.modePaiement = modePaiement;
        this.ReferenceTextuellePreuve = ReferenceTextuellePreuve;
    }

    // Getters and Setters  
    public String getID_Paiement() {
        return ID_Paiement;
    }  
    public void setID_Paiement(String ID_Paiement) {
        this.ID_Paiement = ID_Paiement;
    }
    public int getNumFacture() {
        return NumFacture;
    }
    public void setNumFacture(int NumFacture) {
        this.NumFacture = NumFacture;
    }
    public LocalDate getDatePaiement() {
        return DatePaiement;
    }
    public void setDatePaiement(LocalDate DatePaiement) {
        this.DatePaiement = DatePaiement;
    }
    public double getMontantRecu() {
        return MontantRecu;
    }
    public void setMontantRecu(double MontantRecu) {
        this.MontantRecu = MontantRecu;
    }
    public ModePaiement getModePaiement() {
        return modePaiement;
    }
    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }
    public String getReferenceTextuellePreuve() {
        return ReferenceTextuellePreuve;
    }
    public void setReferenceTextuellePreuve(String ReferenceTextuellePreuve) {
        this.ReferenceTextuellePreuve = ReferenceTextuellePreuve;
    }

    // Override toString method for better representation
     @Override
    public String toString() {
        return "Paiement{" +
                "ID_Paiement='" + ID_Paiement + '\'' +
                ", NumFacture=" + NumFacture +
                ", DatePaiement='" + DatePaiement + '\'' +
                ", MontantRecu=" + MontantRecu +
                ", modePaiement=" + modePaiement +
                ", ReferenceTextuellePreuve='" + ReferenceTextuellePreuve + '\'' +
                '}';
    }

    // Override equals and hashCode methods for proper comparison and hashing
    @Override
    public int hashCode() {
        return ID_Paiement.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Paiement other = (Paiement) obj;
        return this.ID_Paiement.equals(other.ID_Paiement);
    }
    
}
