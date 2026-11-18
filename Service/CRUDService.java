package Service;

import java.util.List;

import Service.ExceptionsServices.ValidationException;
import Service.ExceptionsServices.EntiteIntrouvableException;

public interface CRUDService<T>{
    void ajouter(T entite) throws ValidationException;
    void modifier(T entite) throws EntiteIntrouvableException;
    void supprimer(String id);
    T rechercher(String id);
    List<T> lister();
}
