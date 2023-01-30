package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.AuthorTypes;
import java.util.List;
import java.util.function.Predicate;

public interface IAuthorTypesDAO extends IBaseDAO <AuthorTypes, Long> {
    List<AuthorTypes> getAllAuthorTypes() throws Exception;
    List <AuthorTypes> getAllAuthorTypesBy (Predicate <AuthorTypes> predicate) throws Exception;

}
