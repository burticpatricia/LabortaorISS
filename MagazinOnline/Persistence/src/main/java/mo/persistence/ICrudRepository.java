package mo.persistence;

import mo.model.Entity;

import java.util.List;

public interface ICrudRepository<ID,E extends Entity>{
    public void add(E e);
    public void delete(E e);
    public void update (E e);
    public E findOne(ID id);
    public List<E> findAll();
}