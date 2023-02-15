package pl.solvd.concerthalls.DAO.interfaces;

import pl.solvd.concerthalls.DAO.IBaseDAO;
import pl.solvd.concerthalls.binary.Authors;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public interface IAuthorsDAO extends IBaseDAO<Authors, Long> {
    List<Authors> getAllAuthorsBy(Predicate<Authors> predicate) throws Exception;

    Authors getEntityById(Long id) throws Exception;

    void findAuthorsByAuthorTypesId(Long authorTypesId) throws SQLException;

    List<Authors> findAuthorsByCompositionId(Long compositionId) throws SQLException;
}
