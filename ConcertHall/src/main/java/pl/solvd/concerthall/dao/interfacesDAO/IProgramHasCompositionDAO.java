package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.ProgramHasCompositionEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IProgramHasCompositionDAO extends IBaseDAO <ProgramHasCompositionEntity, Long> {
    List<ProgramHasCompositionEntity> getAllProgramHasComposition() throws Exception;
    List <ProgramHasCompositionEntity> getAllProgramHasCompositionBy (Predicate<ProgramHasCompositionEntity> predicate) throws Exception;
}
