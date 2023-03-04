package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.binary.AuthorsHasAuthorTypes;
import pl.solvd.concerthall.DAO.IBaseDAO;

import java.util.List;
import java.util.function.Predicate;

public interface IAuthorsHasAuthorTypesDAO extends IBaseDAO<AuthorsHasAuthorTypes, Long> {
    List <AuthorsHasAuthorTypes> getAllAuthorsHasAuthorTypesBy (Predicate<AuthorsHasAuthorTypes> predicate) throws Exception;
    void getEntityByAuthorsIdAndAuthorTypesId (Long authorsId, Long authorTypesId) throws Exception;
}
