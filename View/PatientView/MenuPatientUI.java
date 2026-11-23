package View.PatientView;

import java.util.Scanner;

import Controller.PatientController;

public class MenuPatientUI {

    private PatientController controller;
    private Scanner clavier;
    public MenuPatientUI(PatientController controller) {
        this.controller = controller;
        this.clavier = new Scanner(System.in);
    }

    public void start(){
        int choix=-1;
        while (choix != 0) {
            System.out.println("\n=================================");
            System.out.println("      MENU DES PATIENTS");
            System.out.println("");
            System.out.println("1. Ajouter un patient");
            System.out.println("2. Rechercher un patient");
            System.out.println("3. Lister les patients");
            System.out.println("4. Afficher la fiche complète");
            System.out.println("5. Supprimer un patient");
            System.out.println("6. Modifier un patient");
            System.out.println("0. Quitter");
            System.out.print("Donner votre choix : ");

            // On lit le choix (on convertit le texte tapé en chiffre)
            String texteSaisi = clavier.nextLine();
            choix = Integer.parseInt(texteSaisi);

            if(choix==1){
                AjouterPatientUI ui=new AjouterPatientUI();
                ui.afficher(controller, clavier);
            }
            if(choix==2){
                RecherchePatientUI ui=new RecherchePatientUI();
                ui.afficher(controller, clavier);
            }
            if(choix==3){
                ListerPatientUI ui=new ListerPatientUI();
                ui.afficher(controller);
            }
            if(choix==4){
                FichePatientUI ui=new FichePatientUI();
                ui.afficher(controller, clavier);
            }
            if(choix==5){
                SupprimerPatientUI ui=new SupprimerPatientUI();
                ui.afficher(controller, clavier);
            }
            if(choix==6){
                ModifierPatientUI ui=new ModifierPatientUI();
                ui.afficher(controller, clavier);
            }
            if(choix==0){
                System.out.println("***quitter***");
            }else{
                System.out.println("choix invalide ,reessayez encore .");
            }

        }

    
    
    }
}
