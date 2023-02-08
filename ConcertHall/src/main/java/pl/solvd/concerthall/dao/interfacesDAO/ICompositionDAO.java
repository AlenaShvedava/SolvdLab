package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.Composition;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public interface ICompositionDAO extends IBaseDAO <Composition, Long>{
    List <Composition> getAllCompositionBy (Predicate<Composition> predicate) throws Exception;
    List<Composition> findCompositionByAuthorsId(Long authorsId) throws SQLException;
    List<Composition> findCompositionByProgramId(Long programId) throws SQLException;
    Composition getEntityById(Long id) throws Exception;
}
