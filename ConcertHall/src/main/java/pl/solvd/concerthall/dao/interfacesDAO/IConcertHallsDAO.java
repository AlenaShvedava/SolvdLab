package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.ConcertHalls;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public interface IConcertHallsDAO extends IBaseDAO <ConcertHalls, Long> {
    ConcertHalls getEntityById(Long id) throws Exception;
    List <ConcertHalls> getAllConcertHallsBy (Predicate<ConcertHalls> predicate) throws Exception;
    List<ConcertHalls> findConcertHallsByProgramId(Long programId) throws SQLException;
}
