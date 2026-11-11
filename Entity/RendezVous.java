package Entity;

import java.time.LocalDate;
import java.time.LocalTime;
import Entity.enums.StatutRDV;
public class RendezVous {
    private String ID_RDV;
    private LocalDate Date;
    private LocalTime HeureDebut;
    private int DureeMinutes;
    private String Id_Patient;
    private String ID_Medecin,ID_manip,Id_Salle,ID_Examen;
    private StatutRDV statut;
    public RendezVous(String iD_RDV, LocalDate date, LocalTime heureDebut, int dureeMinutes, String id_Patient,
            String iD_Medecin, String iD_manip, String id_Salle, String iD_Examen, StatutRDV statut) {
        ID_RDV = iD_RDV;
        Date = date;
        HeureDebut = heureDebut;
        DureeMinutes = dureeMinutes;
        Id_Patient = id_Patient;
        ID_Medecin = iD_Medecin;
        ID_manip = iD_manip;
        Id_Salle = id_Salle;
        ID_Examen = iD_Examen;
        this.statut = statut;
    }
    public String getID_RDV() {
        return ID_RDV;
    }
    public void setID_RDV(String iD_RDV) {
        ID_RDV = iD_RDV;
    }
    public LocalDate getDate() {
        return Date;
    }
    public void setDate(LocalDate date) {
        Date = date;
    }
    public LocalTime getHeureDebut() {
        return HeureDebut;
    }
    public void setHeureDebut(LocalTime heureDebut) {
        HeureDebut = heureDebut;
    }
    public int getDureeMinutes() {
        return DureeMinutes;
    }
    public void setDureeMinutes(int dureeMinutes) {
        DureeMinutes = dureeMinutes;
    }
    public String getId_Patient() {
        return Id_Patient;
    }
    public void setId_Patient(String id_Patient) {
        Id_Patient = id_Patient;
    }
    public String getID_Medecin() {
        return ID_Medecin;
    }
    public void setID_Medecin(String iD_Medecin) {
        ID_Medecin = iD_Medecin;
    }
    public String getID_manip() {
        return ID_manip;
    }
    public void setID_manip(String iD_manip) {
        ID_manip = iD_manip;
    }
    public String getId_Salle() {
        return Id_Salle;
    }
    public void setId_Salle(String id_Salle) {
        Id_Salle = id_Salle;
    }
    public String getID_Examen() {
        return ID_Examen;
    }
    public void setID_Examen(String iD_Examen) {
        ID_Examen = iD_Examen;
    }
    public StatutRDV getStatut() {
        return statut;
    }
    public void setStatut(StatutRDV statut) {
        this.statut = statut;
    }
    
}
