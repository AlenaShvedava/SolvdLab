package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.DAO.IBaseDAO;
import pl.solvd.concerthall.binary.Poster;
import pl.solvd.concerthall.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public interface IPosterDAO extends IBaseDAO<Poster, Long> {
    List<Poster> getAllPosterBy(Predicate<Poster> predicate);

    Poster getEntityById(Long id);

    List<Program> findProgramsByPosterId(Long posterId);
}
