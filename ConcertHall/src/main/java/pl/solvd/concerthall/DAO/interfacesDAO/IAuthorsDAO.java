package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.DAO.IBaseDAO;
import pl.solvd.concerthall.binary.AuthorTypes;
import pl.solvd.concerthall.binary.Authors;
import pl.solvd.concerthall.binary.Composition;

import java.util.List;
import java.util.function.Predicate;

public interface IAuthorsDAO extends IBaseDAO <Authors, Long> {
    List<Authors> getAllAuthorsBy(Predicate<Authors> predicate) throws Exception;

    Authors getEntityById(Long id);

    List<AuthorTypes> findAuthorTypesByAuthorsId(Long authorsId);

    List<Composition> findCompositionsByAuthorsId(Long compositionId);
}
