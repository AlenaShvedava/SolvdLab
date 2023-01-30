package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;

import pl.solvd.concerthall.entities.PriceLevel;

import java.util.List;
import java.util.function.Predicate;

public interface IPriceLevelDAO extends IBaseDAO <PriceLevel, Long> {
    List<PriceLevel> getAllPriceLevel() throws Exception;
    List <PriceLevel> getAllPriceLevelBy (Predicate<PriceLevel> predicate) throws Exception;
}
