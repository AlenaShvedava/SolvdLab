package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.ProgramHasComposition;

import java.util.List;
import java.util.function.Predicate;

public interface IProgramHasCompositionDAO extends IBaseDAO <ProgramHasComposition, Long> {
    List <ProgramHasComposition> getAllProgramHasCompositionBy (Predicate<ProgramHasComposition> predicate) throws Exception;
    void getEntityByProgramIdAndCompositionId (Long programId, Long compositionId) throws Exception;
}
