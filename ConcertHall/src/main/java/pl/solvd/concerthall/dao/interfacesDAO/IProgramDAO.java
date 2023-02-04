package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.ProgramEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IProgramDAO extends IBaseDAO <ProgramEntity, Long> {
    List<ProgramEntity> getAllProgram () throws Exception;
    List <ProgramEntity> getAllProgramBy (Predicate<ProgramEntity> predicate) throws Exception;
}
