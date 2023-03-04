package pl.solvd.concerthall.services.interfaces;

import pl.solvd.concerthall.binary.PriceLevel;

import java.util.List;
import java.util.function.Predicate;

public interface IPriceLevelService {
    PriceLevel addPriceLevel(PriceLevel priceLevel);

    List<PriceLevel> getAllPriceLevelBy(Predicate<PriceLevel> predicate);

    PriceLevel getPriceLevelById(Long priceLevelId);

    List<PriceLevel> updatePriceLevel(PriceLevel priceLevel);

    void deletePriceLevel(Long id);

    List<PriceLevel> getAll();
}
