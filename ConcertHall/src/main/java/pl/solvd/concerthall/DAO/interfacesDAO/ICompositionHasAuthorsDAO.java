package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.binary.CompositionHasAuthors;
import pl.solvd.concerthall.DAO.IBaseDAO;

import java.util.List;
import java.util.function.Predicate;

public interface ICompositionHasAuthorsDAO extends IBaseDAO<CompositionHasAuthors, Long> {
    List<CompositionHasAuthors> getAllCompositionHasAuthorsBy (Predicate<CompositionHasAuthors> predicate) throws Exception;

    void getEntityByCompositionIdAndAuthorsId (Long compositionId, Long authorsId) throws Exception;
}
