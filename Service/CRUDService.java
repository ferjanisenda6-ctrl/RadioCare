package Service;

import java.util.List;

import Service.ExceptionsServices.ValidationException;
import Service.ExceptionsServices.ConflitException;
import Service.ExceptionsServices.EntiteIntrouvableException;

public interface CRUDService<T>{
    void ajouter(T entite) throws ValidationException,ConflitException;
    void modifier(T entite) throws EntiteIntrouvableException,ValidationException;
    void supprimer(String id) throws ValidationException,EntiteIntrouvableException;
    T rechercher(String id) throws EntiteIntrouvableException;
    List<T> lister();
}
