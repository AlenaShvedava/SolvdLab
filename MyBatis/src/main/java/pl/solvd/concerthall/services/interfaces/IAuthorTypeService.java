package pl.solvd.concerthall.services.interfaces;

import pl.solvd.concerthall.binary.AuthorTypes;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public interface IAuthorTypeService {
    AuthorTypes getEntityById(Long id) throws Exception;
    List<AuthorTypes> updateEntity(AuthorTypes authorTypes);
    void deleteEntity(Long id);
    List<AuthorTypes> getAll();
    AuthorTypes addEntity(AuthorTypes authorTypes);
    void findAuthorsTypesByAuthorsId(Long authorsId) throws SQLException;
    List<AuthorTypes> getAllAuthorTypesBy(Predicate<AuthorTypes> predicate) throws Exception;
}
