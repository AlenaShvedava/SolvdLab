package pl.solvd.concerthall.services;

import pl.solvd.concerthall.entities.Authors;
import pl.solvd.concerthall.exceptions.ServiceException;

public interface AuthorsService  {
    Authors saveEntity (Authors authors) throws Exception;
    Authors getEntityById (Long id) throws Exception;
    void updateEntity (Authors authors) throws ServiceException;
    String deleteEntity (Long id) throws ServiceException;
}