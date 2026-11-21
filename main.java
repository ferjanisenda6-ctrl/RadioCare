import Controller.PatientController;
import Service.PatientService;
import View.PatientView.MenuPatientUI;

public class Main {
    public static void main(String[] args) {
        PatientService service = new PatientService();
        PatientController controller = new PatientController(service);
        
        // On crée le menu et on le démarre
        MenuPatientUI menu = new MenuPatientUI(controller);
        menu.start();
    }
}
