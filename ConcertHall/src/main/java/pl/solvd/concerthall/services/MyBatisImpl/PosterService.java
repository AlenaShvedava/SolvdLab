package pl.solvd.concerthall.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthall.DAO.interfacesDAO.IPosterDAO;
import pl.solvd.concerthall.binary.Poster;
import pl.solvd.concerthall.binary.Program;
import pl.solvd.concerthall.services.interfaces.IPosterService;
import pl.solvd.concerthall.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PosterService implements IPosterService {
    Logger LOG = LogManager.getLogger(PosterService.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();

    @Override
    public Poster addPoster(Poster poster) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IPosterDAO posterDAO = session.getMapper(IPosterDAO.class);
            try {
                posterDAO.addEntity(poster);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return poster;
    }

    @Override
    public List<Poster> getAllPostersBy(Predicate<Poster> predicate) {
        return null;
    }

    @Override
    public Poster getPosterById(Long posterId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IPosterDAO posterDAO = session.getMapper(IPosterDAO.class);
            Poster poster = posterDAO.getEntityById(posterId);
            return poster;
        }
    }

    @Override
    public List<Poster> updatePoster(Poster poster) {
        List<Poster> updatedPoster = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IPosterDAO posterDAO = session.getMapper(IPosterDAO.class);
            try {
                posterDAO.updateEntity(poster);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return updatedPoster;
    }

    @Override
    public void deletePoster(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IPosterDAO posterDAO = session.getMapper(IPosterDAO.class);
            try {
                posterDAO.deleteEntity(id);
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
    public List<Poster> getAll() {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IPosterDAO posterDAO = session.getMapper(IPosterDAO.class);
            List<Poster> list = posterDAO.getAll();
            return list;
        }
    }

    @Override
    public List<Program> findProgramsByPosterId(Long posterId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IPosterDAO posterDAO = session.getMapper(IPosterDAO.class);
            List<Program> programList = posterDAO.findProgramsByPosterId(posterId);
            return programList;
        }
    }
}
