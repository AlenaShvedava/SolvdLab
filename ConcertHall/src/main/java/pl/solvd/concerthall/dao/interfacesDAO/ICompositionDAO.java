package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.CompositionEntity;

import java.util.List;
import java.util.function.Predicate;

public interface ICompositionDAO extends IBaseDAO <CompositionEntity, Long>{
    List<CompositionEntity> getAllComposition() throws Exception;
    List <CompositionEntity> getAllCompositionBy (Predicate<CompositionEntity> predicate) throws Exception;
}
