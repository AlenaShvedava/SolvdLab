package pl.solvd.concerthall.dao;

import java.util.List;

public interface IBaseDAO <T, Id> {
    T saveEntity (T entity) throws Exception;
    void getEntityById (Id id) throws Exception;
    List <T> updateEntity (T entity) throws Exception;
    void deleteEntity (Id id) throws Exception;
}
