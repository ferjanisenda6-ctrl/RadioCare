package View.PatientView;

import Controller.PatientController;

public class ListerPatientUI {

    public void afficher(PatientController controller){
        System.out.println("****LISTE DE TOUS LES PATIENTS****");
        controller.listerPatients();
    }
}
