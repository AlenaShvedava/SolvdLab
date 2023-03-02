package pl.solvd.concerthalls.DAO.interfaces;

import pl.solvd.concerthalls.binary.Images;
import pl.solvd.concerthalls.DAO.IBaseDAO;

import java.util.List;
import java.util.function.Predicate;

public interface IImagesDAO extends IBaseDAO<Images, Long> {
    List<Images> getAllImagesBy(Predicate<Images> predicate);

    Images getEntityById(Long id);
}
