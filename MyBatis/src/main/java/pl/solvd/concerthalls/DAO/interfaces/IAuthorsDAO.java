package pl.solvd.concerthalls.DAO.interfaces;

import pl.solvd.concerthalls.DAO.IBaseDAO;
import pl.solvd.concerthalls.binary.Authors;

import java.util.List;
import java.util.function.Predicate;

public interface IAuthorsDAO extends IBaseDAO <Authors, Long> {
    List<Authors> getAllAuthorsBy(Predicate<Authors> predicate) throws Exception;

    Authors getEntityById(Long id);

    List <Authors> findAuthorsByAuthorTypesId(Long authorTypesId);

    //List<Authors> findAuthorsByCompositionId(Long compositionId) throws SQLException;
}
