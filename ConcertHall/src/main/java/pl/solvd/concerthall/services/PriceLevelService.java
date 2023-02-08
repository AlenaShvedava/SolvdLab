package pl.solvd.concerthall.services;

import pl.solvd.concerthall.dao.impl.PriceLevelDAOImpl;
import pl.solvd.concerthall.dao.interfacesDAO.IPriceLevelDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.PriceLevel;

import java.util.List;
import java.util.function.Predicate;

public class PriceLevelService extends MySqlDAO implements IPriceLevelDAO {
    private final IPriceLevelDAO priceLevelDAO = new PriceLevelDAOImpl();

    @Override
    public PriceLevel addEntity(PriceLevel priceLevel) throws Exception {
        PriceLevel pl = new PriceLevel();
        pl.setType(priceLevel.getType());
        pl.setCoefficient(priceLevel.getCoefficient());
        PriceLevel createdPriceLevel = this.priceLevelDAO.addEntity(pl);
        return priceLevel;
    }

    @Override
    public List<PriceLevel> getAllPriceLevelBy(Predicate<PriceLevel> predicate) throws Exception {
        return priceLevelDAO.getAllPriceLevelBy(predicate);
    }

    @Override
    public PriceLevel getEntityById(Long priceLevelId) throws Exception {
        return priceLevelDAO.getEntityById(priceLevelId);
    }

    @Override
    public List<PriceLevel> updateEntity(PriceLevel priceLevel) throws Exception {
        PriceLevel pl = new PriceLevel();
        pl.setType(priceLevel.getType());
        pl.setCoefficient(priceLevel.getCoefficient());
        pl.setId(PriceLevel.getId());
        return priceLevelDAO.updateEntity(pl);
    }

    @Override
    public void deleteEntity(Long id) throws Exception {
        priceLevelDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<PriceLevel> getAll() throws Exception {
        return priceLevelDAO.getAll();
    }
}
