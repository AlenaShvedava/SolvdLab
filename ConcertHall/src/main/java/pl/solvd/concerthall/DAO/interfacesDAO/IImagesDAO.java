package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthalls.DAO.IBaseDAO;
import pl.solvd.concerthalls.binary.Images;

import java.util.List;
import java.util.function.Predicate;

public interface IImagesDAO extends IBaseDAO<Images, Long> {
    List<Images> getAllImagesBy(Predicate<Images> predicate);

    Images getEntityById(Long id);
}
