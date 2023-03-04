package pl.solvd.concerthall.DAO;

import java.util.List;

public interface IBaseDAO<T, Id> {
    T addEntity(T entity);

    List<T> getAll();

    List<T> updateEntity(T entity);

    void deleteEntity(Id id);
}
