package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.DAO.IBaseDAO;
import pl.solvd.concerthall.binary.Images;

import java.util.List;
import java.util.function.Predicate;

public interface IImagesDAO extends IBaseDAO<Images, Long> {
    List<Images> getAllImagesBy(Predicate<Images> predicate);

    Images getEntityById(Long id);
}
