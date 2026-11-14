package DAO;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T>{
    List<T> findAll();
    Optional<T>findById(String id);//optional alakhter me tnajmch trajaa null (l'id moch null)lezm trajaa l'id
    void save(T entity);//refuse un id deja present
    void update(T entity);
    void delete(String id);
    boolean existsById(String id);
    int count(); //tehseb kadech aandi mn entites par exemples kadech andi mn patients
    

    
}
