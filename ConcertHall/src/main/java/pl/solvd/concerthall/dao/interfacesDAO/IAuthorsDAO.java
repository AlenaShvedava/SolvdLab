package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.Authors;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public interface IAuthorsDAO extends IBaseDAO<Authors, Long> {
    List<Authors> getAllAuthorsBy(Predicate<Authors> predicate) throws Exception;

    Authors getEntityById(Long id) throws Exception;

    List<Authors> findAuthorsByAuthorTypesId(Long authorTypesId) throws SQLException;

    List<Authors> findAuthorsByCompositionId(Long compositionId) throws SQLException;
}
