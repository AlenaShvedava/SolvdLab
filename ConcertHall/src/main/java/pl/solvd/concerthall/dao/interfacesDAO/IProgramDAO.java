package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.Program;

import java.util.List;
import java.util.function.Predicate;

public interface IProgramDAO extends IBaseDAO <Program, Long> {
    List<Program> getAllProgram () throws Exception;
    List <Program> getAllProgramBy (Predicate<Program> predicate) throws Exception;
}
