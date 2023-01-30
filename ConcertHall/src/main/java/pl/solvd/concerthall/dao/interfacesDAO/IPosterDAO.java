package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.Poster;

import java.util.List;
import java.util.function.Predicate;

public interface IPosterDAO extends IBaseDAO <Poster, Long> {
    List<Poster> getAllPoster() throws Exception;
    List <Poster> getAllPosterBy (Predicate<Poster> predicate) throws Exception;
}
