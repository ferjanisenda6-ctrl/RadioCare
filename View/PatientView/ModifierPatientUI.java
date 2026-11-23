package View.PatientView;


import Controller.PatientController;
import Entity.Patient;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class ModifierPatientUI{

    public void afficher(PatientController controller, Scanner clavier) {
        System.out.println("--- MODIFIER UN PATIENT ---");

        try {
            System.out.print("Entrez le CIN du patient à modifier : ");
            String cinAModifier = clavier.nextLine();

            System.out.println("--- Veuillez entrer les NOUVELLES informations ---");

            System.out.print("Nouveau Nom : ");
            String nom = clavier.nextLine();

            System.out.print("Nouveau Prénom : ");
            String prenom = clavier.nextLine();

            System.out.print("Nouvelle Date de Naissance (JJ/MM/AAAA) : ");
            String dateTexte = clavier.nextLine();
            DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateNaissance = LocalDate.parse(dateTexte, formateur);

            System.out.print("Nouveau Téléphone : ");
            String tel = clavier.nextLine();

            System.out.print("Nouvel Email : ");
            String email = clavier.nextLine();

            System.out.print("Nouvelles Contre-indications : ");
            String contreInd = clavier.nextLine();

            System.out.print("Nouveau Numéro d'Assurance : ");
            int numAssurance = clavier.nextInt(); 

            System.out.print("Nouveau Solde du compte : ");
            double solde = clavier.nextDouble(); 


            //la mise a jour du patient
            Patient updPatient = new Patient();
            
            // Le CIN reste le même (ou vous pouvez le changer si vous voulez, mais généralement le CIN ne change pas)
            updPatient.setCIN_Matricule(cinAModifier); 
            updPatient.setNom(nom);
            updPatient.setPrenom(prenom);
            updPatient.setDateNaissance(dateNaissance);
            updPatient.setTelephone(tel);
            updPatient.setEmail(email);
            updPatient.setContreIndication(contreInd);
            updPatient.setNumAssurance(numAssurance);
            updPatient.setSoldeCompte(solde);

            // On envoie la demande de modification au contrôleur
            controller.modifierPatient(cinAModifier, updPatient);

        } catch (DateTimeParseException e) {
            System.out.println("❌ Erreur : Format de date incorrect (utilisez JJ/MM/AAAA).");
        } catch (InputMismatchException e) {
            System.out.println("❌ Erreur : Vous devez taper des chiffres pour l'assurance et le solde !");
            clavier.nextLine(); // Nettoyage
        } catch (Exception e) {
            System.out.println("❌ Erreur : " + e.getMessage());
        }
    }
}