package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.Poster;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public interface IPosterDAO extends IBaseDAO<Poster, Long> {
    List<Poster> getAllPosterBy(Predicate<Poster> predicate) throws Exception;
    Poster getEntityById(Long id) throws Exception;

    List<Poster> findPosterByProgramId(Long programId) throws SQLException;
}
