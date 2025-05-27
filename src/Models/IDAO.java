package Models;

import java.sql.Date;
import java.util.List;

public interface IDAO<T> {
 public void add(T object);
 public void delete(int id);
 public T getOne(int id );
 public List<T> getAll();
 public int nombreCompte();
 public int nombreCompteParDate(Date date);
 public void recupDate();
 
}
