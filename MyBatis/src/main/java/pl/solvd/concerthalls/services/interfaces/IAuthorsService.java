package pl.solvd.concerthalls.services.interfaces;

import pl.solvd.concerthalls.binary.AuthorTypes;
import pl.solvd.concerthalls.binary.Authors;
import pl.solvd.concerthalls.binary.Composition;

import java.util.List;
import java.util.function.Predicate;

public interface IAuthorsService {
    Authors addAuthor(Authors authors);

    List<Authors> getAllAuthorsBy(Predicate<Authors> predicate);

    List<AuthorTypes> findAuthorTypesByAuthorsId(Long authorsId);

    Authors getAuthorById(Long authorsId);

    List<Composition> findCompositionsByAuthorsId(Long authorsId);

    List<Authors> updateAuthor(Authors authors);

    void deleteAuthor(Long id);

    List<Authors> getAll();
}
