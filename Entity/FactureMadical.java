package Entity;

import Entity.enums.StatutPaiement;

public class FactureMadical {
    private int numFacture;
    private String ID_Patient;
    private String ID_RDV;
    private double montantHT;
    private final double TVA=19;
    private double montantTTC;
    private double PartAssurance;
    private double PartPatient;
    private StatutPaiement statutPaiement;

    public FactureMadical(int numFacture, String ID_Patient, String ID_RDV, double montantHT,double PartAssurance,StatutPaiement statutPaiement ) {
        this.numFacture = numFacture;
        this.ID_Patient = ID_Patient;
        this.ID_RDV = ID_RDV;
        this.montantHT = montantHT;
        this.montantTTC = montantHT * (1 + TVA / 100);
        this.PartAssurance = PartAssurance;
        this.PartPatient = montantTTC - PartAssurance;
        this.statutPaiement = statutPaiement;

    }
    public FactureMadical(){

    }
    public int getNumFacture() {
        return numFacture;
    }
    public void setNumFacture(int numFacture) {
        this.numFacture = numFacture;
    }
    public String getID_Patient() {
        return ID_Patient;
    }
    public void setID_Patient(String ID_Patient) {
        this.ID_Patient = ID_Patient;
    }
    public String getID_RDV() {
        return ID_RDV;
    }
    public void setID_RDV(String ID_RDV) {
        this.ID_RDV = ID_RDV;
    }
    public double getMontantHT() {
        return montantHT;
    }
    public void setMontantHT(double montantHT) {
        this.montantHT = montantHT;
    }
    public double getMontantTTC() {
        return montantTTC;
    }
    public void setMontantTTC(double montantTTC) {
        this.montantTTC = montantTTC;
    }

    public double getPartAssurance() {
        return PartAssurance;
    }
    public void setPartAssurance(double partAssurance) {
        PartAssurance = partAssurance;
    }
    public double getPartPatient() {
        return PartPatient;
    }
    public void setPartPatient(double partPatient) {
        PartPatient = partPatient;
    }
    public StatutPaiement getStatutPaiement() {
        return statutPaiement;
    }
    public void setStatutPaiement(StatutPaiement statutPaiement) {
        this.statutPaiement = statutPaiement;
    }
    @Override
    public String toString() {
        return "FactureMedicale{" +
                "numFacture='" + numFacture + '\'' +
                ", ID_Patient='" + ID_Patient + '\'' +
                ", ID_RDV='" + ID_RDV + '\'' +
                ", montantHT=" + montantHT +
                ", montantTTC=" + montantTTC +
                ", PartAssurance=" + PartAssurance +
                ", PartPatient=" + PartPatient +
                ", statutPaiement='" + statutPaiement + '\'' +
                '}';
    }
    

}