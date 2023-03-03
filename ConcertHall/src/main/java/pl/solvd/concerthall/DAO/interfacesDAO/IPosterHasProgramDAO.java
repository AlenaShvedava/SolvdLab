package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.binary.PosterHasProgram;
import pl.solvd.concerthall.DAO.IBaseDAO;

import java.util.List;
import java.util.function.Predicate;

public interface IPosterHasProgramDAO extends IBaseDAO<PosterHasProgram, Long> {
    List<PosterHasProgram> getAllPosterHasProgramBy(Predicate<PosterHasProgram> predicate) throws Exception;

    void getEntityByPosterIdAndProgramId(Long posterId, Long programId) throws Exception;
}
