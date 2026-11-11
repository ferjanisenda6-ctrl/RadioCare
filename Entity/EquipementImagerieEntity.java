package Entity;

import Entity.enums.StatusEquip;
import Entity.enums.Type;

public class EquipementImagerieEntity {
    private String ID_Iquipement;
    private Type type;
    private String Marque;
    private int Heurescumulees;
    private StatusEquip status;



    public EquipementImagerieEntity(String iD_Iquipement, Type type, String marque, int heurescumulees,StatusEquip status) {
        ID_Iquipement = iD_Iquipement;
        this.type = type;
        Marque = marque;
        Heurescumulees = heurescumulees;
        this.status = status;
    }
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
    public StatusEquip getStatus() {
        return status;
    }
    public void setStatus(StatusEquip status) {
        this.status = status;
    }

}