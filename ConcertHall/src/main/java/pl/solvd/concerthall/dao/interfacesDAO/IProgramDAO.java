package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.Program;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public interface IProgramDAO extends IBaseDAO<Program, Long> {
    Program getEntityById(Long id) throws Exception;

    List<Program> getAllProgramBy(Predicate<Program> predicate) throws Exception;

    List<Program> findProgramByConcertHallsId(Long concertHallsId) throws SQLException;
    List<Program> findProgramByCompositionId(Long compositionId) throws SQLException;
    List<Program> findProgramByGenreId(Long genreId) throws SQLException;
    List <Program> findProgramByPosterId(Long posterId) throws SQLException;
}
