package Service;

import DAO.PatientDao;
import DAO.RdvDao;
import Entity.Examen;
import Entity.FactureMedicale;
import Entity.FichePatient;
import Entity.Paiement;
import Entity.Patient;
import Entity.RendezVous;
import DAO.FactureDao;
import DAO.PaiementDao;

import java.util.ArrayList;
import java.util.List;

import DAO.ExamenDao;

public class PatientService implements CRUDService<Patient> {
    private PatientDao patientDao=new PatientDao();
    private RdvDao RdvDao=new RdvDao();
    private FactureDao factureDao=new FactureDao();
    private PaiementDao paiementDao=new PaiementDao();
    private ExamenDao examenDao=new ExamenDao();
    @Override
    public void ajouter(Patient patient) throws ValidationException,ConflitException {
        if(patient==null || patient.getCIN_Matricule()==null || patient.getCIN_Matricule().isEmpty() ){
            throw new ValidationException("le CIN est obligatoire ");

        }
        if(rechercher(patient.getCIN_Matricule())!=null){
            throw new ConflitException("le CIN du patient existe deja");

        }
        patientDao.save(patient); 
        
    }
    @Override
    public void modifier(Patient patient) throws EntiteIntrouvableException {
        if(rechercher(patient.getCIN_Matricule())==null){
            throw new EntiteIntrouvableException("le patient de cin "+patient.getCIN_Matricule()+" est intouvable");
        }
        patientDao.update(patient);

    }
    @Override
    public void supprimer(String cin) throws EntiteIntrouvableException {
        boolean supprime=patientDao.delete(cin);
        if(!supprime){
            throw new EntiteIntrouvableException("impossible de supprimer, patient "+cin+" est introuvable");
        }
    }
    @Override
    public Patient rechercher(String cin){
        return patientDao.findById(cin);

    }
    @Override
    public List<Patient> lister() {
        return patientDao.findAll();
        
    }

    //fonctionnalite complete F: Fiche complete du patient
    public FichePatient getficheComplete(String id) throws EntiteIntrouvableException{
        Patient patient=rechercher(id);
        if(id==null){
            throw new EntiteIntrouvableException("le patient est introuvable");
        
        }
        //historique des examens realisees a travers les rendezvous du patient
        List <Examen> historiqueExamens=new ArrayList<>();
        List <RendezVous> rdvs=RdvDao.findAll();
        for(int i=0;i<rdvs.size();i++){
            RendezVous rdv=rdvs.get(i);
            if(rdv.getId_Patient().equals(id)){
                Examen ex=examenDao.rechercher(rdv.getID_Examen());
                if(ex!=null){
                    historiqueExamens.add(ex);
                }

            }
        }
        //calcul du montant globale et part assurance 
        double montantGlobal=0;
        double partAssurance=0;
        List<FactureMedicale> factures=factureDao.findAll();
        for(int i=0;i<factures.size();i++){
            FactureMedicale f=factures.get(i);
            if(f.getID_Patient().equals(id)){
                montantGlobal+=f.getMontantTTC();
                partAssurance+=f.getPartAssurance();
               
                
            }
        }

        //calcul du montant reglee
        double montantRegle=0;
        List<Paiement> paiements=paiementDao.findAll();
        for(int i=0;i<paiements.size();i++){
            Paiement p=paiements.get(i);
            

        }






    }

    
  



}
