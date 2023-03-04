package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.DAO.IBaseDAO;
import pl.solvd.concerthall.binary.PriceLevel;

import java.util.List;
import java.util.function.Predicate;

public interface IPriceLevelDAO extends IBaseDAO<PriceLevel, Long> {
    List<PriceLevel> getAllPriceLevelBy(Predicate<PriceLevel> predicate);

    PriceLevel getEntityById(Long id);
}
