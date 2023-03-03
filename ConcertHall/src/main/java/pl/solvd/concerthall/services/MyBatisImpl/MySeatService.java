package pl.solvd.concerthall.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthall.DAO.interfacesDAO.IMySeatDAO;
import pl.solvd.concerthall.binary.MySeat;
import pl.solvd.concerthall.services.interfaces.IMySeatService;
import pl.solvd.concerthall.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MySeatService implements IMySeatService {
    Logger LOG = LogManager.getLogger(MySeatService.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();

    @Override
    public MySeat addMySeat(MySeat mySeat) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IMySeatDAO mySeatDAO = session.getMapper(IMySeatDAO.class);
            try {
                mySeatDAO.addEntity(mySeat);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return mySeat;
    }

    @Override
    public List<MySeat> getAllMySeatBy(Predicate<MySeat> predicate) {
        return null;
    }

    @Override
    public MySeat getMySeatById(Long mySeatId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IMySeatDAO mySeatDAO = session.getMapper(IMySeatDAO.class);
            MySeat mySeat = mySeatDAO.getEntityById(mySeatId);
            return mySeat;
        }
    }

    @Override
    public List<MySeat> updateMySeat(MySeat mySeat) {
        List<MySeat> updatedMySeat = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IMySeatDAO mySeatDAO = session.getMapper(IMySeatDAO.class);
            try {
                mySeatDAO.updateEntity(mySeat);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return updatedMySeat;
    }

    @Override
    public void deleteMySeat(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IMySeatDAO mySeatDAO = session.getMapper(IMySeatDAO.class);
            try {
                mySeatDAO.deleteEntity(id);
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
    public List<MySeat> getAll() {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IMySeatDAO mySeatDAO = session.getMapper(IMySeatDAO.class);
            List<MySeat> list = mySeatDAO.getAll();
            return list;
        }
    }
}
