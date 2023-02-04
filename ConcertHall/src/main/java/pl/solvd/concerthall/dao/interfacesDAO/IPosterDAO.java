package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.PosterEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IPosterDAO extends IBaseDAO <PosterEntity, Long> {
    List<PosterEntity> getAllPoster() throws Exception;
    List <PosterEntity> getAllPosterBy (Predicate<PosterEntity> predicate) throws Exception;
}
