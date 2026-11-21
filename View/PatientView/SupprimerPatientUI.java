package View.PatientView;

import java.util.Scanner;

import Controller.PatientController;

public class SupprimerPatientUI {

    public void afficher (PatientController controller, Scanner clavier){
        System.out.println("****SUPPRIMER UN PATIENT****");
        System.out.println("donner le CIN du patient à supprimer : ");
        String CIN=clavier.nextLine();

        System.out.println("Êtes-vous sûr ? (oui / non) : ");
        String reponse = clavier.nextLine();

        if (reponse.equals("oui")){
            controller.supprimerPatient(CIN);
            System.out.println("patient supprimé avec succés .");
        }else{
            System.out.println("Annulation de la suppression .");
        }
    }
    
}
