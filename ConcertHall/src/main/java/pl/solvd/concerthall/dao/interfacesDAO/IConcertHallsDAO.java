package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.DAO.IBaseDAO;
import pl.solvd.concerthall.binary.ConcertHalls;
import pl.solvd.concerthall.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public interface IConcertHallsDAO extends IBaseDAO<ConcertHalls, Long> {
    ConcertHalls getEntityById(Long id);

    List<ConcertHalls> getAllConcertHallsBy(Predicate<ConcertHalls> predicate) throws Exception;

    List<Program> findProgramsByConcertHallId(Long concertHallId);
}
