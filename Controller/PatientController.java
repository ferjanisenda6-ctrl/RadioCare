package Controller;

import java.util.List;

import Entity.Examen;
import Entity.FactureMedicale;
import Entity.FichePatient;
import Entity.Patient;
import Service.PatientService;
import Service.ExceptionsServices.*;

public class PatientController {
    private PatientService patientService ;

    //constructeur :
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //l'ajout d'un patient
    public void ajouterPatient (Patient nouveuPatient){
        try{
            patientService.ajouter(nouveuPatient);
            System.out.println("le nouveau patient a été enregistré dans le système avec succés ");
        }catch(ValidationException e){
            System.out.println("il manque quelques informations :"+e.getMessage());
        }catch(ConflitException e){
            System.out.println(e.getMessage()+" : vérifier quelques informations .");
        }
    }

    // ensuite on va traiter supprimerPatient 
    public void supprimerPatient(String CIN){
        try{
            patientService.supprimer(CIN);
            System.out.println("le patient avec un CIN = "+CIN+" a été supprimer du systéme .");
        }catch(EntiteIntrouvableException e){
            System.out.println("on ne peut pas supprimer le dossier du patient(CIN="+CIN+" )  : "+e.getMessage());
        }
    }

    //afficher un patient 
    public void chercherPatient(String CIN){
        Patient patient = patientService.rechercher(CIN);

        if (patient==null){
            System.out.println("le patient avec CIN = "+CIN+" n'existe pas dans le systéme .");
        }else{
            System.out.println("***** les informations du patient *****");
            System.out.println("l'identifiant : CIN = "+CIN);
            System.out.println("le nom est "+patient.getNom()+" et le prenom "+patient.getPrenom());
            System.out.println("N° de téléphone  : " + patient.getTelephone());
            System.out.println("****************************************\n");
        }
    }

    //la liste des patients qui existent dans le systéme 
    public void listerPatients() {
        List<Patient> liste = patientService.lister();
        
        if (liste.isEmpty()) {
            System.out.println("Notre registre est vide, il n'y a aucun patient d'enregistré pour le moment.");
        } else {
            System.out.println("Registre de nos patients (" + liste.size() + " inscrits) .");
            for (Patient p: liste){
                System.out.println("Non: " + p.getNom() + " , Prenom: " + p.getPrenom() + " (CIN : " + p.getCIN_Matricule() + ")");
            }
            System.out.println("**************************\n");
        }
    }


    //l'affichage de la fiche du patient 
    public void afficherFichePatient(String CIN){
        try{

            //l'affichage complet du fiche patient 
            FichePatient fiche =patientService.getficheComplete(CIN);
            System.out.println("******* DOSSIER COMPLET ********");

            System.out.println("----les informations personnelles ----");
            System.out.println("Patient : " + fiche.getNom() + " " + fiche.getPrenom() + " (CIN : " + fiche.getCIN() + ")");
            System.out.println("Téléphone : " + fiche.getTelephone());
            System.out.println("---------------------------------------------------------");
            
            System.out.println("--- HISTORIQUE MÉDICAL ---");
            System.out.println("Nombre de rendez-vous effectués : " + fiche.getNombreRDV());
            
            List<Examen> examens = fiche.getHistoriqueExamensIDs();
            if (examens.isEmpty()) {
                System.out.println("Aucun examen enregistré pour ce patient.");
            } else {
                System.out.println("Liste des examens (" + examens.size() + ") :");
                for (Examen ex : examens){
                    System.out.println(" Examen ID : " + ex.getID_Examen()); 
                }
            }
            System.out.println("---------------------------------------------------------");
            
            System.out.println(" --- BILAN FINANCIER ---");
            System.out.println("Nombre de factures       : " + fiche.getNombreFactures());
            System.out.println("Montant total facturé    : " + fiche.getTotalFacture() + " DT");
            System.out.println("Prise en charge (CNAM)   : " + fiche.getTotalAssurance() + " DT");
            System.out.println("Part à la charge patient : " + fiche.getTotalPatient() + " DT");
            
            if (fiche.getResteAPayer() > 0) {
                System.out.println("\n Attention : Il reste " + fiche.getResteAPayer() + " DT impayés.");
            } else {
                System.out.println("\n Ce patient est parfaitement à jour dans ses paiements.");
            }
            
            System.out.println("---Plus de details ");
            List<FactureMedicale> factures = fiche.getFactures();
            if (!factures.isEmpty()) {
                System.out.println("\nDétail des factures :");
                for (FactureMedicale f : factures){
                    System.out.println(" Facture n° " + f.getNumFacture() + " | Montant : " + f.getMontantTTC() + " DT");
                }
            }

            System.out.println("********************************");

        }catch(EntiteIntrouvableException e){
            System.out.println("je ne peux pas ouvrir ce dossier parce que :"+e.getMessage());
        }
    }

}
