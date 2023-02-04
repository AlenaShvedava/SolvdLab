package pl.solvd.concerthall.services;

import pl.solvd.concerthall.exceptions.ServiceException;
import pl.solvd.concerthall.models.AuthorsHasAuthorTypes;

public interface AuthorsHasAuthorTypesService {
    AuthorsHasAuthorTypes saveEntity (AuthorsHasAuthorTypes authorsHasAuthorTypes) throws Exception;
    AuthorsHasAuthorTypes getEntityById (Long id) throws Exception;
    void updateEntity (AuthorsHasAuthorTypes authorsHasAuthorTypes) throws ServiceException;
    String deleteEntity (Long id) throws ServiceException;

}
