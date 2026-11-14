package Entity;

import Entity.enums.Specialite;
import java.util.Objects;

public class MedecinRadiologueEntity {
    private String ID_Medecin;
    private int CIN;
    private String nom,prenom;
    private Specialite specialite;
    private double TarifPourcentageActe;
    private boolean DispoPlanning;

<<<<<<< Updated upstream
    // Default constructor
    public MedecinRadiologueEntity() {
    }   

    // Parameterized constructor
=======
    //constructeur vide pour le JSON
    public MedecinRadiologueEntity(){}
    
>>>>>>> Stashed changes
    public MedecinRadiologueEntity(String iD_Medecin, int cIN, String nom, String prenom, Specialite specialite,
            double tarifPourcentageActe, boolean dispoPlanning) {
        ID_Medecin = iD_Medecin;
        CIN = cIN;
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        TarifPourcentageActe = tarifPourcentageActe;
        DispoPlanning = dispoPlanning;
    }

    // Getters and Setters
    public String getID_Medecin() {
        return ID_Medecin;
    }
    public void setID_Medecin(String iD_Medecin) {
        ID_Medecin = iD_Medecin;
    }
    public int getCIN() {
        return CIN;
    }
    public void setCIN(int cIN) {
        CIN = cIN;
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
    public Specialite getSpecialite() {
        return specialite;
    }
    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }
    public double getTarifPourcentageActe() {
        return TarifPourcentageActe;
    }
    public void setTarifPourcentageActe(double tarifPourcentageActe) {
        TarifPourcentageActe = tarifPourcentageActe;
    }
    public boolean isDispoPlanning() {
        return DispoPlanning;
    }
    public void setDispoPlanning(boolean dispoPlanning) {
        DispoPlanning = dispoPlanning;
    }
<<<<<<< Updated upstream

    
=======
    //toString pour afficher les donnees du medecin radiologue
    @Override
    public String toString() {
        return "MedecinRadiologueEntity [ID_Medecin=" + ID_Medecin + ", CIN=" + CIN + ", nom=" + nom + ", prenom="
                + prenom + ", specialite=" + specialite + ", TarifPourcentageActe=" + TarifPourcentageActe
                + ", DispoPlanning=" + DispoPlanning + ", getID_Medecin()=" + getID_Medecin() + ", getCIN()=" + getCIN()
                + ", getNom()=" + getNom() + ", getPrenom()=" + getPrenom() + ", getSpecialite()=" + getSpecialite()
                + ", getTarifPourcentageActe()=" + getTarifPourcentageActe() + ", isDispoPlanning()="
                + isDispoPlanning() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MedecinRadiologueEntity other = (MedecinRadiologueEntity) obj;
        return Objects.equals(CIN, other.CIN);
     
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(CIN);
 
    }

    

>>>>>>> Stashed changes
    
}












