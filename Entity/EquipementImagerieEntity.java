package Entity;

import Entity.enums.StatutEquip;
import Entity.enums.Type;

public class EquipementImagerieEntity {
    private String ID_Iquipement;
    private Type type;
    private String Marque;
    private int Heurescumulees;
    private StatutEquip status;


    // Constructors
    public EquipementImagerieEntity(String iD_Iquipement, Type type, String marque, int heurescumulees,StatutEquip status) {
        ID_Iquipement = iD_Iquipement;
        this.type = type;
        Marque = marque;
        Heurescumulees = heurescumulees;
        this.status = status;
    }
    //constructor vide pour la sérialisation JSON
    public EquipementImagerieEntity() {
    }

    // Getters and Setters
    public String getID_Iquipement() {
        return ID_Iquipement;
    }
    public void setID_Iquipement(String iD_Iquipement) {
        ID_Iquipement = iD_Iquipement;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public String getMarque() {
        return Marque;
    }
    public void setMarque(String marque) {
        Marque = marque;
    }
    public int getHeurescumulees() {
        return Heurescumulees;
    }
    public void setHeurescumulees(int heurescumulees) {
        Heurescumulees = heurescumulees;
    }
    public StatutEquip getStatus() {
        return status;
    }
    public void setStatus(StatutEquip status) {
        this.status = status;
    }

    // Override equals and hashCode methods for proper comparison and hashing
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EquipementImagerieEntity other = (EquipementImagerieEntity) obj;
        return this.ID_Iquipement.equals(other.ID_Iquipement);
    }
   
    @Override
    public int hashCode() {
        return ID_Iquipement.hashCode();
    }
    // Override toString method for better representation 
     @Override
    public String toString() {
        return "EquipementImagerieEntity [ID_Iquipement=" + ID_Iquipement + ", type=" + type + ", Marque=" + Marque
                + ", Heurescumulees=" + Heurescumulees + ", status=" + status + "]";
    }

}