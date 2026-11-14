package Entity;


import Entity.enums.Libelle;

public class ExamenEntity {

    private String ID_Examen;
    private String CodeActe;
    private Libelle libelle;
    private int DureeEstimeeMinutes;
    private double TarifHT;

    // constructeur vide (utilisé pour la lecture du JSON)
    public ExamenEntity() {

    }
    // constructeur avec paramètres
    public ExamenEntity(String idExamen, String codeActe, Libelle libelle, int dureeMinutes, double tarifHT) {
        this.ID_Examen = idExamen;
        this.CodeActe = codeActe;
        this.libelle = libelle;
        this.DureeEstimeeMinutes = dureeMinutes;
        this.TarifHT = tarifHT;
    }

    // Getters and Setters

    public String getID_Examen() {
        return ID_Examen;
    }
    public void setID_Examen(String iD_Examen) {
        ID_Examen = iD_Examen;
    }

    public String getCodeActe() { return CodeActe; }
    public void setCodeActe(String codeActe) { this.CodeActe = codeActe; }

    public Libelle getLibelle() { return libelle; }
    public void setLibelle(Libelle libelle) { this.libelle = libelle; }

    public int getDureeMinutes() { return DureeEstimeeMinutes; }
    public void setDureeMinutes(int dureeMinutes) { this.DureeEstimeeMinutes = dureeMinutes; }

    public double getTarifHT() { return TarifHT; }
    public void setTarifHT(double tarifHT) { this.TarifHT = tarifHT; }

    public String getId() { return ID_Examen; }

    public boolean equals(ExamenEntity other) {
        if (this == other) return true;
        if (other == null) return false;
        return this.ID_Examen.equals(other.ID_Examen);
    }

    // Override toString method for better representation
    @Override
    public String toString() {
        return "ExamenEntity [ID_Examen=" + ID_Examen + ", CodeActe=" + CodeActe + ", libelle=" + libelle
                + ", DureeEstimeeMinutes=" + DureeEstimeeMinutes + ", TarifHT=" + TarifHT + "]";
    }

    // Override equals and hashCode methods for proper comparison and hashing
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ExamenEntity other = (ExamenEntity) obj;
        return ID_Examen.equals(other.ID_Examen);
    }
    @Override
    public int hashCode() {
        return ID_Examen.hashCode();
    }
    

}

