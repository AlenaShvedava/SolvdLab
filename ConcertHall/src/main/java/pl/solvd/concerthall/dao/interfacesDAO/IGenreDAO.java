package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.GenreEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IGenreDAO extends IBaseDAO <GenreEntity, Long> {
    List<GenreEntity> getAllGenre() throws Exception;
    List <GenreEntity> getAllGenreBy (Predicate<GenreEntity> predicate) throws Exception;
}
