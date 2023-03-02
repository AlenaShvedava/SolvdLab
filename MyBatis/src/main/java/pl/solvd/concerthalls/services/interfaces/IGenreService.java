package pl.solvd.concerthalls.services.interfaces;

import pl.solvd.concerthalls.binary.Events;
import pl.solvd.concerthalls.binary.Genre;
import pl.solvd.concerthalls.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public interface IGenreService {
    Genre addGenre(Genre genre);

    List<Genre> getAllGenreBy(Predicate<Genre> predicate);

    Genre getGenreById(Long genreId);

    List<Program> findProgramsByGenreId(Long genreId);

    List<Genre> updateGenre(Genre genre);

    void deleteGenre(Long id);

    List<Genre> getAll();

    List<Events> findEventsByGenreId(Long genreId);
}
