package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.Genre;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public interface IGenreDAO extends IBaseDAO <Genre, Long> {
    Genre getEntityById(Long id) throws Exception;
    List <Genre> getAllGenreBy (Predicate<Genre> predicate) throws Exception;
    List<Genre> findGenreByProgramId(Long programId) throws SQLException;
}
