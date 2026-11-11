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

        ID_Examen = "";
        CodeActe = "";
    }

    public ExamenEntity(String idExamen, String codeActe, Libelle libelle, int dureeMinutes, double tarifHT) {
        this.ID_Examen = idExamen;
        this.CodeActe = codeActe;
        this.libelle = libelle;
        this.DureeEstimeeMinutes = dureeMinutes;
        this.TarifHT = tarifHT;
    }

    public String getIdExamen() { return ID_Examen; }
    public void setIdExamen(String idExamen) { this.ID_Examen = idExamen; }

    public String getCodeActe() { return CodeActe; }
    public void setCodeActe(String codeActe) { this.CodeActe = codeActe; }

    public Libelle getLibelle() { return libelle; }
    public void setLibelle(Libelle libelle) { this.libelle = libelle; }

    public int getDureeMinutes() { return DureeEstimeeMinutes; }
    public void setDureeMinutes(int dureeMinutes) { this.DureeEstimeeMinutes = dureeMinutes; }

    public double getTarifHT() { return TarifHT; }
    public void setTarifHT(double tarifHT) { this.TarifHT = tarifHT; }

    public String getId() { return ID_Examen; }

    
    

}

