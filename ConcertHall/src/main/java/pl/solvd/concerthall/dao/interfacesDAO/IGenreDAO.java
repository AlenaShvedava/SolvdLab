package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.DAO.IBaseDAO;
import pl.solvd.concerthall.binary.Events;
import pl.solvd.concerthall.binary.Genre;
import pl.solvd.concerthall.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public interface IGenreDAO extends IBaseDAO<Genre, Long> {
    Genre getEntityById(Long id);

    List<Genre> getAllGenreBy(Predicate<Genre> predicate) throws Exception;

    List<Program> findProgramsByGenreId(Long genreId);

    List<Events> findEventsByGenreId(Long genreId);
}
