package pl.solvd.concerthalls.DAO.interfaces;

import pl.solvd.concerthalls.DAO.IBaseDAO;
import pl.solvd.concerthalls.binary.Poster;
import pl.solvd.concerthalls.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public interface IPosterDAO extends IBaseDAO<Poster, Long> {
    List<Poster> getAllPosterBy(Predicate<Poster> predicate);

    Poster getEntityById(Long id);

    List<Program> findProgramsByPosterId(Long posterId);
}
