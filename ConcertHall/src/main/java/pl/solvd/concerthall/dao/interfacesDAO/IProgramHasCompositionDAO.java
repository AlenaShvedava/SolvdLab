package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.ProgramHasComposition;

import java.util.List;
import java.util.function.Predicate;

interface IProgramHasCompositionDAO extends IBaseDAO <ProgramHasComposition, Long> {
    List<ProgramHasComposition> getAllProgramHasComposition() throws Exception;
    List <ProgramHasComposition> getAllProgramHasCompositionBy (Predicate<ProgramHasComposition> predicate) throws Exception;
}
