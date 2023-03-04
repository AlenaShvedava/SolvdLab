package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.binary.ProgramHasGenre;
import pl.solvd.concerthall.DAO.IBaseDAO;

import java.util.List;
import java.util.function.Predicate;

public interface IProgramHasGenreDAO extends IBaseDAO<ProgramHasGenre, Long> {
    List<ProgramHasGenre> getAllProgramHasGenreBy(Predicate<ProgramHasGenre> predicate) throws Exception;

    void getEntityByProgramIdAndGenreId(Long programId, Long genreId) throws Exception;
}
