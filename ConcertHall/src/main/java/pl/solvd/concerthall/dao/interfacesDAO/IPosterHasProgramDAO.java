package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;

import pl.solvd.concerthall.entities.PosterHasProgramEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IPosterHasProgramDAO extends IBaseDAO <PosterHasProgramEntity, Long> {
    List<PosterHasProgramEntity> getAllPosterHasProgram() throws Exception;
    List <PosterHasProgramEntity> getAllPosterHasProgramBy (Predicate<PosterHasProgramEntity> predicate) throws Exception;
}
