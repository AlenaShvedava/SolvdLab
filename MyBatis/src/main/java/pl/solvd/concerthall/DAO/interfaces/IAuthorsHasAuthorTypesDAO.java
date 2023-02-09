package pl.solvd.concerthall.DAO.interfaces;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.binary.AuthorsHasAuthorTypes;

import java.util.List;
import java.util.function.Predicate;

public interface IAuthorsHasAuthorTypesDAO extends IBaseDAO <AuthorsHasAuthorTypes, Long> {
    List <AuthorsHasAuthorTypes> getAllAuthorsHasAuthorTypesBy (Predicate<AuthorsHasAuthorTypes> predicate) throws Exception;
    void getEntityByAuthorsIdAndAuthorTypesId (Long authorsId, Long authorTypesId) throws Exception;
}
