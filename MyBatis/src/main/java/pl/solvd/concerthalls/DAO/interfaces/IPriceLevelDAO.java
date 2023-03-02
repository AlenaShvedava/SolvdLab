package pl.solvd.concerthalls.DAO.interfaces;

import pl.solvd.concerthalls.binary.PriceLevel;
import pl.solvd.concerthalls.DAO.IBaseDAO;

import java.util.List;
import java.util.function.Predicate;

public interface IPriceLevelDAO extends IBaseDAO<PriceLevel, Long> {
    List<PriceLevel> getAllPriceLevelBy(Predicate<PriceLevel> predicate);

    PriceLevel getEntityById(Long id);
}
