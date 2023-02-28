package pl.solvd.concerthalls.DAO;

import java.util.List;

public interface IBaseDAO<T, Id> {
    T addEntity (T entity) throws Exception;
    List <T> getAll();
    List <T> updateEntity (T entity) throws Exception;
    void deleteEntity (Id id) throws Exception;
}
