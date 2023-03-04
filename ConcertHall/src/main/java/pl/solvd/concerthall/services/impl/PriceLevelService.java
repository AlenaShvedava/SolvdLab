package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.DAO.impl.PriceLevelDAOImpl;
import pl.solvd.concerthall.DAO.interfacesDAO.IPriceLevelDAO;
import pl.solvd.concerthall.binary.PriceLevel;

import java.util.List;
import java.util.function.Predicate;

public class PriceLevelService implements IPriceLevelDAO {
    private final IPriceLevelDAO priceLevelDAO = new PriceLevelDAOImpl();

    @Override
    public PriceLevel addEntity(PriceLevel priceLevel) {
        PriceLevel pl = new PriceLevel();
        pl.setType(priceLevel.getType());
        pl.setCoefficient(priceLevel.getCoefficient());
        PriceLevel createdPriceLevel = this.priceLevelDAO.addEntity(pl);
        return priceLevel;
    }

    @Override
    public List<PriceLevel> getAllPriceLevelBy(Predicate<PriceLevel> predicate) {
        return priceLevelDAO.getAllPriceLevelBy(predicate);
    }

    @Override
    public PriceLevel getEntityById(Long priceLevelId) {
        return priceLevelDAO.getEntityById(priceLevelId);
    }

    @Override
    public List<PriceLevel> updateEntity(PriceLevel priceLevel) {
        PriceLevel pl = new PriceLevel();
        pl.setType(priceLevel.getType());
        pl.setCoefficient(priceLevel.getCoefficient());
        pl.setId(PriceLevel.getId());
        return priceLevelDAO.updateEntity(pl);
    }

    @Override
    public void deleteEntity(Long id) {
        priceLevelDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<PriceLevel> getAll() {
        return priceLevelDAO.getAll();
    }
}
