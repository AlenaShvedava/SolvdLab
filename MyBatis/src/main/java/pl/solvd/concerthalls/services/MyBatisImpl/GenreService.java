package pl.solvd.concerthalls.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthalls.DAO.interfaces.IGenreDAO;
import pl.solvd.concerthalls.binary.Events;
import pl.solvd.concerthalls.binary.Genre;
import pl.solvd.concerthalls.binary.Program;
import pl.solvd.concerthalls.services.interfaces.IGenreService;
import pl.solvd.concerthalls.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class GenreService implements IGenreService {
    Logger LOG = LogManager.getLogger(GenreService.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();

    @Override
    public Genre addGenre(Genre genre) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IGenreDAO genreDAO = session.getMapper(IGenreDAO.class);
            try {
                genreDAO.addEntity(genre);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return genre;
    }

    @Override
    public List<Genre> getAllGenreBy(Predicate<Genre> predicate) {
        return null;
    }

    @Override
    public Genre getGenreById(Long genreId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IGenreDAO genreDAO = session.getMapper(IGenreDAO.class);
            Genre genre = genreDAO.getEntityById(genreId);
            return genre;
        }
    }

    @Override
    public List<Genre> updateGenre(Genre genre) {
        List<Genre> updatedGenre = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IGenreDAO genreDAO = session.getMapper(IGenreDAO.class);
            try {
                genreDAO.updateEntity(genre);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return updatedGenre;
    }

    @Override
    public void deleteGenre(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IGenreDAO genreDAO = session.getMapper(IGenreDAO.class);
            try {
                genreDAO.deleteEntity(id);
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
    public List<Genre> getAll() {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IGenreDAO genreDAO = session.getMapper(IGenreDAO.class);
            List<Genre> list = genreDAO.getAll();
            return list;
        }
    }

    @Override
    public List<Program> findProgramsByGenreId(Long genreId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IGenreDAO genreDAO = session.getMapper(IGenreDAO.class);
            List<Program> programsList = genreDAO.findProgramsByGenreId(genreId);
            return programsList;
        }
    }

    @Override
    public List<Events> findEventsByGenreId(Long genreId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IGenreDAO genreDAO = session.getMapper(IGenreDAO.class);
            List<Events> eventsList = genreDAO.findEventsByGenreId(genreId);
            return eventsList;
        }
    }
}
