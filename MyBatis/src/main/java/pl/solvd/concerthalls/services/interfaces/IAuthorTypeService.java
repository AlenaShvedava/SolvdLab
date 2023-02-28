package pl.solvd.concerthalls.services.interfaces;

import pl.solvd.concerthalls.binary.AuthorTypes;

import java.util.List;
import java.util.function.Predicate;

public interface IAuthorTypeService {
    AuthorTypes getEntityById(Long id) throws Exception;
    List<AuthorTypes> updateEntity(AuthorTypes authorTypes);
    void deleteEntity(Long id);
    List<AuthorTypes> getAll();
    AuthorTypes addEntity(AuthorTypes authorTypes);
    List <AuthorTypes> findAuthorsTypesByAuthorsId(Long authorsId);
    List<AuthorTypes> getAllAuthorTypesBy(Predicate<AuthorTypes> predicate) throws Exception;
}
