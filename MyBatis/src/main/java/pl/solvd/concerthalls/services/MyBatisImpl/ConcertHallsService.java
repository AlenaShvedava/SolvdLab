package pl.solvd.concerthalls.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthalls.DAO.interfaces.IConcertHallsDAO;
import pl.solvd.concerthalls.binary.ConcertHalls;
import pl.solvd.concerthalls.binary.Program;
import pl.solvd.concerthalls.services.interfaces.IConcertHallsService;
import pl.solvd.concerthalls.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ConcertHallsService implements IConcertHallsService {
    Logger LOG = LogManager.getLogger(ConcertHallsService.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();

    @Override
    public ConcertHalls addConcertHall(ConcertHalls concertHall) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IConcertHallsDAO concertHallDAO = session.getMapper(IConcertHallsDAO.class);
            try {
                concertHallDAO.addEntity(concertHall);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return concertHall;
    }

    @Override
    public List<ConcertHalls> getAllConcertHallsBy(Predicate<ConcertHalls> predicate) {
        return null;
    }

    @Override
    public ConcertHalls getConcertHallById(Long concertHallId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IConcertHallsDAO concertHallDAO = session.getMapper(IConcertHallsDAO.class);
            ConcertHalls concertHall = concertHallDAO.getEntityById(concertHallId);
            return concertHall;
        }
    }

    @Override
    public List<ConcertHalls> updateConcertHall(ConcertHalls concertHall) {
        List<ConcertHalls> updatedConcertHall = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IConcertHallsDAO concertHallDAO = session.getMapper(IConcertHallsDAO.class);
            try {
                concertHallDAO.updateEntity(concertHall);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return updatedConcertHall;
    }

    @Override
    public void deleteConcertHall(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IConcertHallsDAO concertHallDAO = session.getMapper(IConcertHallsDAO.class);
            try {
                concertHallDAO.deleteEntity(id);
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
    public List<ConcertHalls> getAll() {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IConcertHallsDAO concertHallDAO = session.getMapper(IConcertHallsDAO.class);
            List<ConcertHalls> list = concertHallDAO.getAll();
            return list;
        }
    }

    @Override
    public List<Program> findProgramsByConcertHallId(Long concertHallId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IConcertHallsDAO concertHallDAO = session.getMapper(IConcertHallsDAO.class);
            List<Program> programsList = concertHallDAO.findProgramsByConcertHallId(concertHallId);
            return programsList;
        }
    }
}
