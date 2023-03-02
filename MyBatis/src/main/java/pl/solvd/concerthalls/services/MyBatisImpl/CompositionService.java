package pl.solvd.concerthalls.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthalls.DAO.interfaces.ICompositionDAO;
import pl.solvd.concerthalls.binary.Authors;
import pl.solvd.concerthalls.binary.Composition;
import pl.solvd.concerthalls.binary.Program;
import pl.solvd.concerthalls.services.interfaces.ICompositionService;
import pl.solvd.concerthalls.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CompositionService implements ICompositionService {
    Logger LOG = LogManager.getLogger(CompositionService.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();

    @Override
    public Composition addComposition(Composition composition) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            ICompositionDAO compositionDAO = session.getMapper(ICompositionDAO.class);
            try {
                compositionDAO.addEntity(composition);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return composition;
    }

    @Override
    public List<Composition> getAllCompositionsBy(Predicate<Composition> predicate) {
        return null;
    }

    @Override
    public Composition getCompositionById(Long compositionId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            ICompositionDAO compositionDAO = session.getMapper(ICompositionDAO.class);
            Composition composition = compositionDAO.getEntityById(compositionId);
            return composition;
        }
    }

    @Override
    public List<Composition> updateComposition(Composition composition) {
        List<Composition> updatedComposition = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            ICompositionDAO compositionDAO = session.getMapper(ICompositionDAO.class);
            try {
                compositionDAO.updateEntity(composition);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return updatedComposition;
    }

    @Override
    public void deleteComposition(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            ICompositionDAO compositionDAO = session.getMapper(ICompositionDAO.class);
            try {
                compositionDAO.deleteEntity(id);
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
    public List<Composition> getAll() {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            ICompositionDAO compositionDAO = session.getMapper(ICompositionDAO.class);
            List<Composition> list = compositionDAO.getAll();
            return list;
        }
    }

    @Override
    public List<Authors> findAuthorsByCompositionId(Long compositionId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            ICompositionDAO compositionDAO = session.getMapper(ICompositionDAO.class);
            List<Authors> authorsList = compositionDAO.findAuthorsByCompositionId(compositionId);
            return authorsList;
        }
    }

    @Override
    public List<Program> findProgramsByCompositionId(Long compositionId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            ICompositionDAO compositionDAO = session.getMapper(ICompositionDAO.class);
            List<Program> programsList = compositionDAO.findProgramsByCompositionId(compositionId);
            return programsList;
        }
    }
}
