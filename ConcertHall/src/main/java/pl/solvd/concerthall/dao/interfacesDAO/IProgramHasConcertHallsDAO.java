package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.Authors;
import pl.solvd.concerthall.entities.ProgramHasConcertHalls;

import java.util.List;
import java.util.function.Predicate;

public interface IProgramHasConcertHallsDAO extends IBaseDAO <ProgramHasConcertHalls, Long> {
    List<ProgramHasConcertHalls> getAllProgramHasConcertHalls() throws Exception;
    List <ProgramHasConcertHalls> getAllProgramHasConcertHallsBy (Predicate<Authors> predicate) throws Exception;
}
