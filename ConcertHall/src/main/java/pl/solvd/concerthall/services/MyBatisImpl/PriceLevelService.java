package pl.solvd.concerthall.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthall.DAO.interfacesDAO.IPriceLevelDAO;
import pl.solvd.concerthall.binary.PriceLevel;
import pl.solvd.concerthall.services.interfaces.IPriceLevelService;
import pl.solvd.concerthall.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PriceLevelService implements IPriceLevelService {
    Logger LOG = LogManager.getLogger(PriceLevelService.class);
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();

    @Override
    public PriceLevel addPriceLevel(PriceLevel priceLevel) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IPriceLevelDAO priceLevelDAO = session.getMapper(IPriceLevelDAO.class);
            try {
                priceLevelDAO.addEntity(priceLevel);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return priceLevel;
    }

    @Override
    public List<PriceLevel> getAllPriceLevelBy(Predicate<PriceLevel> predicate) {
        return null;
    }

    @Override
    public PriceLevel getPriceLevelById(Long priceLevelId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IPriceLevelDAO priceLevelDAO = session.getMapper(IPriceLevelDAO.class);
            PriceLevel priceLevel = priceLevelDAO.getEntityById(priceLevelId);
            return priceLevel;
        }
    }

    @Override
    public List<PriceLevel> updatePriceLevel(PriceLevel priceLevel) {
        List<PriceLevel> updatedPriceLevel = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IPriceLevelDAO priceLevelDAO = session.getMapper(IPriceLevelDAO.class);
            try {
                priceLevelDAO.updateEntity(priceLevel);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return updatedPriceLevel;
    }

    @Override
    public void deletePriceLevel(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IPriceLevelDAO priceLevelDAO = session.getMapper(IPriceLevelDAO.class);
            try {
                priceLevelDAO.deleteEntity(id);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public List<PriceLevel> getAll() {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IPriceLevelDAO priceLevelDAO = session.getMapper(IPriceLevelDAO.class);
            List<PriceLevel> list = priceLevelDAO.getAll();
            return list;
        }
    }
}
