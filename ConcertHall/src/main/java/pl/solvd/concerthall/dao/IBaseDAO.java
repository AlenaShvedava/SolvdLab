package pl.solvd.concerthall.dao;

import pl.solvd.concerthall.entities.Authors;
import pl.solvd.concerthall.exceptions.ServiceException;

import java.util.List;

public interface IBaseDAO <T, Id> {
    T saveEntity (T entity) throws Exception;
    void getEntityById (Id id) throws Exception;
    List <T> updateEntity (T entity) throws Exception;
    void deleteEntity (Id id) throws Exception;
}
