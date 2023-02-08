package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.ProgramHasConcertHalls;

import java.util.List;
import java.util.function.Predicate;

public interface IProgramHasConcertHallsDAO extends IBaseDAO <ProgramHasConcertHalls, Long> {
    List <ProgramHasConcertHalls> getAllProgramHasConcertHallsBy (Predicate<ProgramHasConcertHalls> predicate) throws Exception;
    void getEntityByProgramIdAndConcertHallsId (Long programId, Long concertHallsId) throws Exception;
}
