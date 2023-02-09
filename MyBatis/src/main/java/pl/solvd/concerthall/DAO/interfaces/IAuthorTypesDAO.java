package pl.solvd.concerthall.DAO.interfaces;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.binary.AuthorTypes;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public interface IAuthorTypesDAO extends IBaseDAO<AuthorTypes, Long> {
    List<AuthorTypes> getAllAuthorTypesBy(Predicate<AuthorTypes> predicate) throws Exception;

    AuthorTypes getEntityById(Long id) throws Exception;

    List<AuthorTypes> findAuthorsTypesByAuthorsId(Long authorsId) throws SQLException;
}
