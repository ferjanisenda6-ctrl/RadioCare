Package RadioCare.Entity;

public class PatientEntity {
    private int CIN_Matricule;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private int telephone ;
    private String Email;
    private int NumAssurance;
    private double SoldeCompte;
    private ExamenEntity[] examens;

    public PatientEntity(int CIN_Matricule, String nom, String prenom, String dateNaissance, int telephone, String Email, int NumAssurance, double SoldeCompte) {
        this.CIN_Matricule = CIN_Matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.Email = Email;
        this.NumAssurance = NumAssurance;
        this.SoldeCompte = SoldeCompte;
    }
    public int getCIN_Matricule() {
        return CIN_Matricule;
    }
}

