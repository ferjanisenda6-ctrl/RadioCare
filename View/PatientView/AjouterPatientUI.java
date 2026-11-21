package View.PatientView;


import Controller.PatientController;
import Entity.Patient;
import java.util.Scanner;

public class AjouterPatientUI {

    public void afficher(PatientController controller, Scanner clavier) {
        System.out.println("\n--- AJOUTER UN PATIENT ---");

        System.out.print("Entrez le CIN : ");
        String cin = clavier.nextLine();

        System.out.print("Entrez le Nom : ");
        String nom = clavier.nextLine();

        System.out.print("Entrez le Prénom : ");
        String prenom = clavier.nextLine();

        System.out.print("Entrez le Téléphone : ");
        String tel = clavier.nextLine();

        // On crée le patient avec les infos tapées
        Patient p = new Patient();
        p.setCIN_Matricule(cin);
        p.setNom(nom);
        p.setPrenom(prenom);
        p.setTelephone(tel);

        try {
            controller.ajouterPatient(p);
            System.out.println("Patient ajouté avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}