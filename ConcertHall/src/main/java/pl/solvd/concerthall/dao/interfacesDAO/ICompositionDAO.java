package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.DAO.IBaseDAO;
import pl.solvd.concerthall.binary.Authors;
import pl.solvd.concerthall.binary.Composition;
import pl.solvd.concerthall.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public interface ICompositionDAO extends IBaseDAO<Composition, Long> {
    Composition getEntityById(Long id);

    List<Composition> getAllCompositionBy(Predicate<Composition> predicate);

    List<Authors> findAuthorsByCompositionId(Long authorsId);

    List<Program> findProgramsByCompositionId(Long programId);
}
