package pl.solvd.concerthalls.DAO.interfaces;

import pl.solvd.concerthalls.DAO.IBaseDAO;
import pl.solvd.concerthalls.binary.AuthorsHasAuthorTypes;

import java.util.List;
import java.util.function.Predicate;

public interface IAuthorsHasAuthorTypesDAO extends IBaseDAO<AuthorsHasAuthorTypes, Long> {
    List<AuthorsHasAuthorTypes> getAllAuthorsHasAuthorTypesBy (Predicate<AuthorsHasAuthorTypes> predicate) throws Exception;
    void getEntityByAuthorsIdAndAuthorTypesId (Long authorsId, Long authorTypesId) throws Exception;
}
