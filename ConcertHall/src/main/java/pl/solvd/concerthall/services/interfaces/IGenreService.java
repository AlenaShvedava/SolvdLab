package pl.solvd.concerthall.services.interfaces;

import pl.solvd.concerthall.binary.Events;
import pl.solvd.concerthall.binary.Genre;
import pl.solvd.concerthall.binary.Program;

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
