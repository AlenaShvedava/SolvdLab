package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.CompositionHasAuthorsEntity;

import java.util.List;
import java.util.function.Predicate;

public interface ICompositionHasAuthorsDAO extends IBaseDAO <CompositionHasAuthorsEntity, Long>{
    List<CompositionHasAuthorsEntity> getAllCompositionHasAuthors() throws Exception;
    List <CompositionHasAuthorsEntity> getAllCompositionHasAuthorsBy (Predicate<CompositionHasAuthorsEntity> predicate) throws Exception;
}
