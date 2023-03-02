package pl.solvd.concerthalls.DAO.interfaces;

import pl.solvd.concerthalls.DAO.IBaseDAO;
import pl.solvd.concerthalls.binary.ConcertHalls;
import pl.solvd.concerthalls.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public interface IConcertHallsDAO extends IBaseDAO<ConcertHalls, Long> {
    ConcertHalls getEntityById(Long id);

    List<ConcertHalls> getAllConcertHallsBy(Predicate<ConcertHalls> predicate) throws Exception;

    List<Program> findProgramsByConcertHallId(Long concertHallId);
}
