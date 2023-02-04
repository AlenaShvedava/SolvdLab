package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.AuthorsEntity;
import pl.solvd.concerthall.entities.ProgramHasConcertHallsEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IProgramHasConcertHallsDAO extends IBaseDAO <ProgramHasConcertHallsEntity, Long> {
    List<ProgramHasConcertHallsEntity> getAllProgramHasConcertHalls() throws Exception;
    List <ProgramHasConcertHallsEntity> getAllProgramHasConcertHallsBy (Predicate<ProgramHasConcertHallsEntity> predicate) throws Exception;
}
