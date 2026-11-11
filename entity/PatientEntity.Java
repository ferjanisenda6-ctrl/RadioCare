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
    private List<String> historiqueExamensIDs;

    public Patient() {
        this.historiqueExamensIDs = new ArrayList<>();
    }

    public PatientEntity(int CIN_Matricule, String nom, String prenom, String dateNaissance, int telephone, String Email, int NumAssurance, double SoldeCompte,String contreIndication,List<String> historiqueExamensIDs) {
        this.CIN_Matricule = CIN_Matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.Email = Email;
        this.NumAssurance = NumAssurance;
        this.SoldeCompte = SoldeCompte;
        this.contreIndication = contreIndication;
        this.historiqueExamensIDs = historiqueExamensIDs;
    }
    public int getCIN_Matricule() {
        return CIN_Matricule;
    }
    public void setCIN_Matricule(int CIN_Matricule) {
        this.CIN_Matricule = CIN_Matricule;
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
    public String getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public int getTelephone() {
        return telephone;
    }
    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public int getNumAssurance() {
        return NumAssurance;
    }
    public void setNumAssurance(int numAssurance) {
        NumAssurance = numAssurance;
    }
    public double getSoldeCompte() {
        return SoldeCompte;
    }
    public void setSoldeCompte(double soldeCompte) {
        SoldeCompte = soldeCompte;
    }
    public ExamenEntity[] getExamens() {
        return examens;
    }


}


