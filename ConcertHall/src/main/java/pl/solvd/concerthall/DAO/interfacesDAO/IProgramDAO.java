package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.DAO.IBaseDAO;
import pl.solvd.concerthall.binary.*;

import java.util.List;
import java.util.function.Predicate;

public interface IProgramDAO extends IBaseDAO <Program, Long> {
    Program getEntityById(Long id);

    List<Program> getAllProgramBy(Predicate<Program> predicate);

    List<ConcertHalls> findConcertHallByProgramId(Long programId);
    List<Composition> findCompositionByProgramId(Long programId);
    List<Genre> findGenreByProgramId(Long programId);
    List <Poster> findPostersByProgramId(Long programId);
    List<Images> findImagesByProgramId(Long programId);
}
