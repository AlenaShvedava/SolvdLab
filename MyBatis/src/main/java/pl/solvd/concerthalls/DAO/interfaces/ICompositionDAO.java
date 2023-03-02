package pl.solvd.concerthalls.DAO.interfaces;

import pl.solvd.concerthalls.DAO.IBaseDAO;
import pl.solvd.concerthalls.binary.Authors;
import pl.solvd.concerthalls.binary.Composition;
import pl.solvd.concerthalls.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public interface ICompositionDAO extends IBaseDAO<Composition, Long> {
    Composition getEntityById(Long id);

    List<Composition> getAllCompositionBy(Predicate<Composition> predicate);

    List<Authors> findAuthorsByCompositionId(Long authorsId);

    List<Program> findProgramsByCompositionId(Long programId);
}
