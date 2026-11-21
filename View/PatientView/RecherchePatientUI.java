package View.PatientView;

import java.util.Scanner;

import Controller.PatientController;

public class RecherchePatientUI {
    
    public void afficher(PatientController controller , Scanner clavier){
        System.out.println("---La recherche du patient---");
        System.out.println("Donner la CIN du patient : ");
        String CIN=clavier.nextLine();

        controller.chercherPatient(CIN);
    }
}
