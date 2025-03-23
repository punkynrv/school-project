package md.ceiti.ma.indfxhibernate.model.interfaces;

import java.util.List;

public interface CrudInterface <T>{
    T get(int id);
    List<T> getAll();
    void delete(int id);
    void update(T obj);
    void create(T obj);
}