package pl.solvd.concerthalls.DAO.interfaces;


import pl.solvd.concerthalls.DAO.IBaseDAO;
import pl.solvd.concerthalls.binary.AuthorTypes;
import pl.solvd.concerthalls.binary.Authors;

import java.util.List;
import java.util.function.Predicate;

public interface IAuthorTypesDAO extends IBaseDAO<AuthorTypes, Long> {
    List<AuthorTypes> getAllAuthorTypesBy(Predicate<AuthorTypes> predicate) throws Exception;

    AuthorTypes getEntityById(Long id);

    List<Authors> findAuthorsByAuthorTypesId(Long authorTypesId);
}
