package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.CompositionHasAuthors;

import java.util.List;
import java.util.function.Predicate;

public interface ICompositionHasAuthorsDAO extends IBaseDAO<CompositionHasAuthors, Long> {
    List<CompositionHasAuthors> getAllCompositionHasAuthorsBy (Predicate<CompositionHasAuthors> predicate) throws Exception;

    void getEntityByCompositionIdAndAuthorsId (Long compositionId, Long authorsId) throws Exception;
}
