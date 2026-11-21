package View.PatientView;


import Controller.PatientController;
import Entity.Patient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        System.out.print("Entrez la Date de Naissance (ex: 01/01/1990) : ");
        String dateNaissance = clavier.nextLine();
        DateTimeFormatter formateur=DateTimeFormatter.ofPattern("dd/MM/yyyy");//comme si on attend le format du dd/MM/yyyy 

        System.out.print("Entrez le Téléphone : ");
        String tel = clavier.nextLine();

        System.out.print("Entrez l'Email : ");
        String email = clavier.nextLine();

        System.out.print("Entrez les Contre-indications (ou tapez 'aucune') : ");
        String contreInd = clavier.nextLine();

        // On pose la question pour l'Assurance (int)
        System.out.print("Entrez le Numéro d'Assurance : ");
        String numTexte = clavier.nextLine();
        int numAssurance = Integer.parseInt(numTexte); // Transforme le texte en entier

        // On pose la question pour le Solde (double)
        System.out.print("Entrez le Solde du compte : ");
        String soldeTexte = clavier.nextLine();
        double solde = Double.parseDouble(soldeTexte); // Transforme le texte en nombre à virgule

        // === CRÉATION DU PATIENT ===
        Patient p = new Patient();
            
        p.setCIN_Matricule(cin);
        p.setNom(nom);
        p.setPrenom(prenom);
        p.setDateNaissance(LocalDate.parse(dateNaissance, formateur));
        p.setTelephone(tel);
        p.setEmail(email);
        p.setContreIndication(contreInd);
        p.setNumAssurance(numAssurance);
        p.setSoldeCompte(solde);
        
        // On envoie le patient rempli au contrôleur
        controller.ajouterPatient(p);
        System.out.println("Patient ajouté avec succès !");
        try {
            controller.ajouterPatient(p);
            System.out.println("Patient ajouté avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}