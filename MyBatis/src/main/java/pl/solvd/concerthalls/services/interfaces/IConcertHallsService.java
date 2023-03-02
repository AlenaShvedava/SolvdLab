package pl.solvd.concerthalls.services.interfaces;

import pl.solvd.concerthalls.binary.ConcertHalls;
import pl.solvd.concerthalls.binary.Program;

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
