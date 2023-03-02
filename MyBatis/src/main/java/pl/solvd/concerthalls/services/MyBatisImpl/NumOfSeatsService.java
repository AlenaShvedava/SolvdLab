package pl.solvd.concerthalls.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthalls.DAO.interfaces.INumOfSeatsDAO;
import pl.solvd.concerthalls.binary.NumOfSeats;
import pl.solvd.concerthalls.services.interfaces.INumOfSeatsService;
import pl.solvd.concerthalls.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class NumOfSeatsService implements INumOfSeatsService {
    Logger LOG = LogManager.getLogger(NumOfSeatsService.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();

    @Override
    public NumOfSeats addNumOfSeats(NumOfSeats numOfSeats) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            INumOfSeatsDAO numOfSeatsDAO = session.getMapper(INumOfSeatsDAO.class);
            try {
                numOfSeatsDAO.addEntity(numOfSeats);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return numOfSeats;
    }

    @Override
    public List<NumOfSeats> getAllNumOfSeatsBy(Predicate<NumOfSeats> predicate) {
        return null;
    }

    @Override
    public NumOfSeats getNumOfSeatsById(Long numOfSeatsId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            INumOfSeatsDAO numOfSeatsDAO = session.getMapper(INumOfSeatsDAO.class);
            NumOfSeats numOfSeats = numOfSeatsDAO.getEntityById(numOfSeatsId);
            return numOfSeats;
        }
    }

    @Override
    public List<NumOfSeats> updateNumOfSeats(NumOfSeats numOfSeats) {
        List<NumOfSeats> updatedNumOfSeats = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            INumOfSeatsDAO numOfSeatsDAO = session.getMapper(INumOfSeatsDAO.class);
            try {
                numOfSeatsDAO.updateEntity(numOfSeats);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return updatedNumOfSeats;
    }

    @Override
    public void deleteNumOfSeats(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            INumOfSeatsDAO numOfSeatsDAO = session.getMapper(INumOfSeatsDAO.class);
            try {
                numOfSeatsDAO.deleteEntity(id);
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
    public List<NumOfSeats> getAll() {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            INumOfSeatsDAO numOfSeatsDAO = session.getMapper(INumOfSeatsDAO.class);
            List<NumOfSeats> list = numOfSeatsDAO.getAll();
            return list;
        }
    }
}
