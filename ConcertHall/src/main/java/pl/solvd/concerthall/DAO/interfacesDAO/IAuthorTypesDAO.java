package pl.solvd.concerthall.DAO.interfacesDAO;


import pl.solvd.concerthall.DAO.IBaseDAO;
import pl.solvd.concerthall.binary.AuthorTypes;
import pl.solvd.concerthall.binary.Authors;

import java.util.List;
import java.util.function.Predicate;

public interface IAuthorTypesDAO extends IBaseDAO<AuthorTypes, Long> {
    List<AuthorTypes> getAllAuthorTypesBy(Predicate<AuthorTypes> predicate) throws Exception;

    AuthorTypes getEntityById(Long id);

    List<Authors> findAuthorsByAuthorTypesId(Long authorTypesId);
}
