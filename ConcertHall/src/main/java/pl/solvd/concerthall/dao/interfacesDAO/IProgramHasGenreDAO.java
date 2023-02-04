package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;

import pl.solvd.concerthall.entities.ProgramHasGenreEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IProgramHasGenreDAO extends IBaseDAO <ProgramHasGenreEntity, Long> {
    List<ProgramHasGenreEntity> getAllProgramHasGenre () throws Exception;
    List <ProgramHasGenreEntity> getAllProgramHasGenreBy (Predicate<ProgramHasGenreEntity> predicate) throws Exception;
}
