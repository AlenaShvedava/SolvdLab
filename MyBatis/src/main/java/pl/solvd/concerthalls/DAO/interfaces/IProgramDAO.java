package pl.solvd.concerthalls.DAO.interfaces;

import pl.solvd.concerthalls.DAO.IBaseDAO;
import pl.solvd.concerthalls.binary.*;

import java.util.List;
import java.util.function.Predicate;

public interface IProgramDAO extends IBaseDAO<Program, Long> {
    Program getEntityById(Long id);

    List<Program> getAllProgramsBy(Predicate<Program> predicate) throws Exception;

    List<ConcertHalls> findConcertHallByProgramId(Long programId);

    List<Composition> findCompositionByProgramId(Long programId);

    List<Genre> findGenreByProgramId(Long programId);

    List<Poster> findPostersByProgramId(Long programId);

    List<Images> findImagesByProgramId(Long programId);
}
