package pl.solvd.concerthall.services;

import pl.solvd.concerthall.entities.Authors;
import pl.solvd.concerthall.exceptions.ServiceException;
import pl.solvd.concerthall.models.AuthorTypes;

public interface AuthorTypesService {
    AuthorTypes saveEntity (AuthorTypes authorTypes) throws Exception;
    AuthorTypes getEntityById (Long id) throws Exception;
    void updateEntity (AuthorTypes authorTypes) throws ServiceException;
    String deleteEntity (Long id) throws ServiceException;
}
