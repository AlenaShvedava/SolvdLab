package pl.solvd.concerthall.services.interfaces;

import pl.solvd.concerthall.binary.AuthorTypes;
import pl.solvd.concerthall.binary.Authors;

import java.util.List;
import java.util.function.Predicate;

public interface IAuthorTypeService {
    AuthorTypes getAuthorTypeById(Long id);

    List<AuthorTypes> updateAuthorType(AuthorTypes authorTypes);

    void deleteAuthorType(Long id);

    List<AuthorTypes> getAll();

    AuthorTypes addAuthorType(AuthorTypes authorTypes);

    List<Authors> findAuthorsByAuthorTypesId(Long authorTypesId);

    //    List <AuthorTypes> findAuthorsTypesByAuthorsId(Long authorsId);
    List<AuthorTypes> getAllAuthorTypesBy(Predicate<AuthorTypes> predicate) throws Exception;
}
