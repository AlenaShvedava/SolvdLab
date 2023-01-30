package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.Genre;

import java.util.List;
import java.util.function.Predicate;

public interface IGenreDAO extends IBaseDAO <Genre, Long> {
    List<Genre> getAllGenre() throws Exception;
    List <Genre> getAllGenreBy (Predicate<Genre> predicate) throws Exception;
}
