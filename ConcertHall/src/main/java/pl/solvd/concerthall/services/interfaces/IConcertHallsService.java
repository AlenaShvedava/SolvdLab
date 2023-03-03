package pl.solvd.concerthall.services.interfaces;

import pl.solvd.concerthall.binary.ConcertHalls;
import pl.solvd.concerthall.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public interface IConcertHallsService {
    ConcertHalls addConcertHall(ConcertHalls concertHall);

    List<ConcertHalls> getAllConcertHallsBy(Predicate<ConcertHalls> predicate);

    List<Program> findProgramsByConcertHallId(Long concertHallId);

    ConcertHalls getConcertHallById(Long concertHallId);

    List<ConcertHalls> updateConcertHall(ConcertHalls concertHall);

    void deleteConcertHall(Long id);

    List<ConcertHalls> getAll();
}
