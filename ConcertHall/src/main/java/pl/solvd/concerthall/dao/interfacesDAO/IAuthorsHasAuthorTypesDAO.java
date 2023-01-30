package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.AuthorsHasAuthorTypes;

import java.util.List;
import java.util.function.Predicate;

public interface IAuthorsHasAuthorTypesDAO extends IBaseDAO <AuthorsHasAuthorTypes, Long>{
    List<AuthorsHasAuthorTypes> getAllAuthorsHasAuthorTypes() throws Exception;
    List <AuthorsHasAuthorTypes> getAllAuthorsHasAuthorTypesBy (Predicate<AuthorsHasAuthorTypes> predicate) throws Exception;

}
