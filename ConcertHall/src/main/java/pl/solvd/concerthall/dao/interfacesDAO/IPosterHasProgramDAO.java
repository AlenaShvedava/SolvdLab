package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;

import pl.solvd.concerthall.entities.PosterHasProgram;

import java.util.List;
import java.util.function.Predicate;

public interface IPosterHasProgramDAO extends IBaseDAO<PosterHasProgram, Long> {
    List<PosterHasProgram> getAllPosterHasProgramBy(Predicate<PosterHasProgram> predicate) throws Exception;

    void getEntityByPosterIdAndProgramId(Long posterId, Long programId) throws Exception;
}
