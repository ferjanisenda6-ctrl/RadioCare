package Entity;

import Entity.enums.Type;

public class SalleRadiologie {
    private String Id_Salle;
    private String nom;
    private int NumeroEtage;
    private Type TypeSalle;
    private String EquipementAttribueID;
    private String NormeBlindagePlomb;
    private boolean StatutDispo;
    // Constructor
    public SalleRadiologie(String id_Salle, String nom, int numeroEtage, Type typeSalle, String equipementAttribueID,
            String normeBlindagePlomb, boolean statutDispo) {
        Id_Salle = id_Salle;
        this.nom = nom;
        NumeroEtage = numeroEtage;
        TypeSalle = typeSalle;
        EquipementAttribueID = equipementAttribueID;
        NormeBlindagePlomb = normeBlindagePlomb;
        StatutDispo = statutDispo;
    }
    // default constructor
    public SalleRadiologie() {
    }

    // Getters & Setters

    public String getId_Salle() {
        return Id_Salle;
    }
    public void setId_Salle(String id_Salle) {
        Id_Salle = id_Salle;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getNumeroEtage() {
        return NumeroEtage;
    }
    public void setNumeroEtage(int numeroEtage) {
        NumeroEtage = numeroEtage;
    }
    public Type getTypeSalle() {
        return TypeSalle;
    }
    public void setTypeSalle(Type typeSalle) {
        TypeSalle = typeSalle;
    }
    public String getEquipementAttribueID() {
        return EquipementAttribueID;
    }
    public void setEquipementAttribueID(String equipementAttribueID) {
        EquipementAttribueID = equipementAttribueID;
    }
    public String getNormeBlindagePlomb() {
        return NormeBlindagePlomb;
    }
    public void setNormeBlindagePlomb(String normeBlindagePlomb) {
        NormeBlindagePlomb = normeBlindagePlomb;
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
        return "SalleRadiologie [Id_Salle=" + Id_Salle + ", nom=" + nom + ", NumeroEtage=" + NumeroEtage
                + ", TypeSalle=" + TypeSalle + ", EquipementAttribueID=" + EquipementAttribueID
                + ", NormeBlindagePlomb=" + NormeBlindagePlomb + ", StatutDispo=" + StatutDispo + "]";
    }

    // Override equals and hashCode methods for proper comparison and hashing
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SalleRadiologie other = (SalleRadiologie) obj;
        return Id_Salle.equals(other.Id_Salle);
    }
    @Override
    public int hashCode() {
        return Id_Salle.hashCode();
    }   


}
