package pl.solvd.concerthalls.DAO.interfaces;

import pl.solvd.concerthalls.DAO.IBaseDAO;
import pl.solvd.concerthalls.binary.AuthorTypes;
import pl.solvd.concerthalls.binary.Authors;
import pl.solvd.concerthalls.binary.Composition;

import java.util.List;
import java.util.function.Predicate;

public interface IAuthorsDAO extends IBaseDAO<Authors, Long> {
    List<Authors> getAllAuthorsBy(Predicate<Authors> predicate) throws Exception;

    Authors getEntityById(Long id);

    List<AuthorTypes> findAuthorTypesByAuthorsId(Long authorsId);

    List<Composition> findCompositionsByAuthorsId(Long compositionId);
}
