package View.PatientView;

import Controller.PatientController;
import java.util.Scanner;

public class FichePatientUI {

    public void afficher(PatientController controller , Scanner clavier){
        System.out.println("**** DOSSIER COMPLET DU PATIENT ****");
        System.out.println("Donner le CIN du patient : ");
        String CIN =clavier.nextLine();

        controller.afficherFichePatient(CIN);
    }
    
}
