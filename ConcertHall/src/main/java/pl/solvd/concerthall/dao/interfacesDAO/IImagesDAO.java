package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.ImagesEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IImagesDAO extends IBaseDAO <ImagesEntity, Long> {
    List<ImagesEntity> getAllImages() throws Exception;
    List <ImagesEntity> getAllImagesBy (Predicate<ImagesEntity> predicate) throws Exception;
}
