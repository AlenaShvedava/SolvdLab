package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.Images;

import java.util.List;
import java.util.function.Predicate;

public interface IImagesDAO extends IBaseDAO <Images, Long> {
    List <Images> getAllImagesBy (Predicate<Images> predicate) throws Exception;
    Images getEntityById(Long id) throws Exception;
}
