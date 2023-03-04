package pl.solvd.concerthall.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthall.DAO.interfacesDAO.IProgramDAO;
import pl.solvd.concerthall.binary.*;
import pl.solvd.concerthall.services.interfaces.IProgramService;
import pl.solvd.concerthall.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ProgramService implements IProgramService {
    Logger LOG = LogManager.getLogger(ProgramService.class);
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();

    @Override
    public Program addProgram(Program program) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IProgramDAO programDAO = session.getMapper(IProgramDAO.class);
            try {
                programDAO.addEntity(program);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return program;
    }

    @Override
    public List<Program> getAllProgramsBy(Predicate<Program> predicate) {
        return null;
    }

    @Override
    public Program getProgramById(Long programId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IProgramDAO programDAO = session.getMapper(IProgramDAO.class);
            return programDAO.getEntityById(programId);
        }
    }

    @Override
    public List<Program> updateProgram(Program program) {
        List<Program> updatedProgram = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IProgramDAO programDAO = session.getMapper(IProgramDAO.class);
            try {
                programDAO.updateEntity(program);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return updatedProgram;
    }

    @Override
    public void deleteProgram(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IProgramDAO programDAO = session.getMapper(IProgramDAO.class);
            try {
                programDAO.deleteEntity(id);
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
    public List<Program> getAll() {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IProgramDAO programDAO = session.getMapper(IProgramDAO.class);
            List<Program> list = programDAO.getAll();
            return list;
        }
    }

    @Override
    public List<Composition> findCompositionByProgramId(Long programId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IProgramDAO programDAO = session.getMapper(IProgramDAO.class);
            List<Composition> compositionList = programDAO.findCompositionByProgramId(programId);
            return compositionList;
        }
    }

    @Override
    public List<Genre> findGenreByProgramId(Long programId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IProgramDAO programDAO = session.getMapper(IProgramDAO.class);
            List<Genre> genreList = programDAO.findGenreByProgramId(programId);
            return genreList;
        }
    }

    @Override
    public List<ConcertHalls> findConcertHallByProgramId(Long programId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IProgramDAO programDAO = session.getMapper(IProgramDAO.class);
            List<ConcertHalls> concertHallList = programDAO.findConcertHallByProgramId(programId);
            return concertHallList;
        }
    }

    @Override
    public List<Poster> findPostersByProgramId(Long programId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IProgramDAO programDAO = session.getMapper(IProgramDAO.class);
            List<Poster> postersList = programDAO.findPostersByProgramId(programId);
            return postersList;
        }
    }

    @Override
    public List<Images> findImagesByProgramId(Long programId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IProgramDAO programDAO = session.getMapper(IProgramDAO.class);
            List<Images> imagesList = programDAO.findImagesByProgramId(programId);
            return imagesList;
        }
    }
}
