package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.binary.ProgramHasComposition;
import pl.solvd.concerthall.DAO.IBaseDAO;

import java.util.List;
import java.util.function.Predicate;

public interface IProgramHasCompositionDAO extends IBaseDAO<ProgramHasComposition, Long> {
    List <ProgramHasComposition> getAllProgramHasCompositionBy (Predicate<ProgramHasComposition> predicate) throws Exception;
    void getEntityByProgramIdAndCompositionId (Long programId, Long compositionId) throws Exception;
}
