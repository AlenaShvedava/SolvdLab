package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.binary.ProgramHasConcertHalls;
import pl.solvd.concerthall.DAO.IBaseDAO;

import java.util.List;
import java.util.function.Predicate;

public interface IProgramHasConcertHallsDAO extends IBaseDAO<ProgramHasConcertHalls, Long> {
    List <ProgramHasConcertHalls> getAllProgramHasConcertHallsBy (Predicate<ProgramHasConcertHalls> predicate) throws Exception;
    void getEntityByProgramIdAndConcertHallsId (Long programId, Long concertHallsId) throws Exception;
}
