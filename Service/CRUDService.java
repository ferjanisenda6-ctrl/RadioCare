package Service;

import java.util.List;

public interface CRUDService<T>{
    void ajouter(T entite);
    void modifier(T entite);
    void supprimer(String id);
    T rechercher(String id);
    List<T> lister();

}
