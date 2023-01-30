package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.Composition;

import java.util.List;
import java.util.function.Predicate;

public interface ICompositionDAO extends IBaseDAO <Composition, Long>{
    List<Composition> getAllComposition() throws Exception;
    List <Composition> getAllCompositionBy (Predicate<Composition> predicate) throws Exception;
}
