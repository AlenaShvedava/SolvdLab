package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;

import pl.solvd.concerthall.entities.PriceLevelEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IPriceLevelDAO extends IBaseDAO <PriceLevelEntity, Long> {
    List<PriceLevelEntity> getAllPriceLevel() throws Exception;
    List <PriceLevelEntity> getAllPriceLevelBy (Predicate<PriceLevelEntity> predicate) throws Exception;
}
