package Entity;

import Entity.enums.StatutEquip;
import Entity.enums.Type;

public class EquipementImagerie {
    private String ID_Equipement;
    private Type type;
    private String Marque;
    private int Heurescumulees;
    private StatutEquip status;


    // Constructors
    public EquipementImagerie(String iD_Equipement, Type type, String marque, int heurescumulees,StatutEquip status) {
        ID_Equipement = iD_Equipement;
        this.type = type;
        Marque = marque;
        Heurescumulees = heurescumulees;
        this.status = status;
    }
    //constructor vide pour la sérialisation JSON
    public EquipementImagerie() {
    }

    // Getters and Setters
    public String getID_Equipement() {
        return ID_Equipement;
    }
    public void setID_Iquipement(String iD_Equipement) {
        ID_Equipement = iD_Equipement;
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
        EquipementImagerie other = (EquipementImagerie) obj;
        return this.ID_Equipement.equals(other.ID_Equipement);
    }
   
    @Override
    public int hashCode() {
        return ID_Equipement.hashCode();
    }
    // Override toString method for better representation 
     @Override
    public String toString() {
        return "EquipementImagerieEntity [ID_Iquipement=" + ID_Equipement + ", type=" + type + ", Marque=" + Marque
                + ", Heurescumulees=" + Heurescumulees + ", status=" + status + "]";
    }

}