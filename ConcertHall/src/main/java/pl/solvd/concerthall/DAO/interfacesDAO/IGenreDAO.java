package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthalls.DAO.IBaseDAO;
import pl.solvd.concerthalls.binary.Events;
import pl.solvd.concerthalls.binary.Genre;
import pl.solvd.concerthalls.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public interface IGenreDAO extends IBaseDAO<Genre, Long> {
    Genre getEntityById(Long id);

    List<Genre> getAllGenreBy(Predicate<Genre> predicate) throws Exception;

    List<Program> findProgramsByGenreId(Long genreId);

    List<Events> findEventsByGenreId(Long genreId);
}
