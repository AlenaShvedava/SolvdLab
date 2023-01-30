package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;

import pl.solvd.concerthall.entities.PosterHasProgram;

import java.util.List;
import java.util.function.Predicate;

public interface IPosterHasProgramDAO extends IBaseDAO <PosterHasProgram, Long> {
    List<PosterHasProgram> getAllPosterHasProgram() throws Exception;
    List <PosterHasProgram> getAllPosterHasProgramBy (Predicate<PosterHasProgram> predicate) throws Exception;
}
