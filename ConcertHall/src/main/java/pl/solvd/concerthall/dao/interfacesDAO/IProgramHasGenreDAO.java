package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;

import pl.solvd.concerthall.entities.ProgramHasGenre;

import java.util.List;
import java.util.function.Predicate;

public interface IProgramHasGenreDAO extends IBaseDAO <ProgramHasGenre, Long> {
    List<ProgramHasGenre> getAllProgramHasGenre () throws Exception;
    List <ProgramHasGenre> getAllProgramHasGenreBy (Predicate<ProgramHasGenre> predicate) throws Exception;
}
