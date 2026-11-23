package Service;

import Entity.MedecinRadiologue;
import Entity.RendezVous;
import Entity.enums.StatutRDV;

import DAO.MedecinDao;
import DAO.RdvDao;

import Service.ExceptionsServices.ValidationException;
import Service.ExceptionsServices.EntiteIntrouvableException;

import java.util.List;
 

public class MedecinService implements CRUDService<MedecinRadiologue>{

    private final MedecinDao medecinDao;
    private final RdvDao rdvDao;

    //constructeur 
    public MedecinService(MedecinDao medecinDao ,RdvDao rdvDao){
        this.medecinDao=medecinDao;
        this.rdvDao=rdvDao;

    }


    // CRUD 
    @Override
    public void ajouter(MedecinRadiologue medecin) throws ValidationException{
        validerMedecin(medecin);// on va verifier la validité du medecin qui est en parametre

        if(medecinDao.existsById(medecin.getID_Medecin()))
            throw new ValidationException("le medecin associé à l'ID "+medecin.getID_Medecin()+" existe déjà .");
        medecinDao.save(medecin);
    }

    @Override
    public void modifier(String id,MedecinRadiologue medecin) throws EntiteIntrouvableException,ValidationException{
        
        validerMedecin(medecin);// on va verifier la validité du medecin qui est en parametre

        MedecinRadiologue medecinRadiologue = medecinDao.findById(medecin.getID_Medecin());
        // si le medecin n'eexiste pas
        if (medecinRadiologue == null){
            throw new EntiteIntrouvableException("le médecin est introuvable : " + medecin.getID_Medecin());
        }

        List<MedecinRadiologue> medecins =medecinDao.findAll();//liste de tous les medecins
        
        for (MedecinRadiologue m : medecins) {
            if(m.getID_Medecin().equals(medecin.getID_Medecin())==false)
                throw new ValidationException("Il existe un médecin qui porte le meme ID");
        }
        medecinDao.update(medecin);

    }

    @Override
    public void supprimer(String id) throws ValidationException,EntiteIntrouvableException {
        List<RendezVous> listeRdv= rdvDao.findAll();//donne la liste des rendez vous
        boolean RDVactif=false;

        for(RendezVous rdv: listeRdv){
            if(rdv.getID_Medecin().equals(id) && rdv.getStatut() != StatutRDV.ANNULE){
                RDVactif=true;
                break;//il existe un rendez vous donc on ne peut pas supprimer le medecin
            }
        }
        // on a trouver que le medecin possede des rendez vous donc on a bloqué la suppression .
        if( RDVactif==true){
            throw new ValidationException("Impossible de supprimer ce médecin car il a un rendez vous actif");
        }

        boolean supprime=medecinDao.delete(id);//la suppression du médecin

        //juste une simple verification si le DAO 
        if (supprime == false) {
            throw new EntiteIntrouvableException("Médecin introuvable pour suppression.");
        }
    }

    @Override
    public java.util.List<MedecinRadiologue> lister() {
        return medecinDao.findAll();
    }

    @Override
    public MedecinRadiologue rechercher(String id) throws EntiteIntrouvableException{
        MedecinRadiologue medecin = medecinDao.findById(id);
        if (medecin == null) {
            throw new EntiteIntrouvableException("Médecin introuvable : " + id);
        }
        return medecin;
    }


    // on doit verifier la validité des attributs du médecin et cette methode affiche les exceptions 
    private void validerMedecin(MedecinRadiologue m) throws ValidationException {
        if (m.getCIN() <10000000 || m.getCIN()>99999999 )
            throw new ValidationException("Le CIN du médecin est obligatoire et comporte 8 chiffres.");
        if( m.getNom() == null || m.getPrenom() == null  )
            throw new ValidationException("Le nom et le prénom ne peuvent pas être vides.");
        if(m.getSpecialite() ==null)
            throw new ValidationException("le médecin doit avoir une spécialité");
        if(m.getTarifPourcentageActe() <0 || m.getTarifPourcentageActe()>100)
            throw new ValidationException("le pourcentage tarifaire doit etre entre 0 et 100 ");

    }

}
