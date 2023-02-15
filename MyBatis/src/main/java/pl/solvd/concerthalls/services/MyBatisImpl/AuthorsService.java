package pl.solvd.concerthalls.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthalls.DAO.interfaces.IAuthorsDAO;
import pl.solvd.concerthalls.DAO.mysql.MySqlDAO;
import pl.solvd.concerthalls.binary.Authors;
import pl.solvd.concerthalls.services.interfaces.IAuthorsService;
import pl.solvd.concerthalls.utils.MyBatisFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AuthorsService extends MySqlDAO implements IAuthorsService {
    Logger LOG = LogManager.getLogger(AuthorsService.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();
    @Override
    public Authors addEntity(Authors authors) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorsDAO authorsDAO = session.getMapper(IAuthorsDAO.class);
            try {
                authorsDAO.addEntity(authors);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return authors;
    }

    @Override
    public List<Authors> getAllAuthorsBy(Predicate<Authors> predicate) {
        return null;
    }

    @Override
    public Authors getEntityById(Long authorsId) {
        Authors author;
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorsDAO authorsDAO = session.getMapper(IAuthorsDAO.class);
            try {
                author = authorsDAO.getEntityById(authorsId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return author;
    }

    @Override
    public void findAuthorsByAuthorTypesId(Long authorTypesId) {
        List<Authors> authorsList = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorsDAO authorsDAO = session.getMapper(IAuthorsDAO.class);
            try {
                authorsDAO.findAuthorsByAuthorTypesId(authorTypesId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void findAuthorsByCompositionId(Long compositionId) {
        List<Authors> authorsList = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorsDAO authorsDAO = session.getMapper(IAuthorsDAO.class);
            try {
                authorsDAO.findAuthorsByCompositionId(compositionId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Authors> updateEntity(Authors authors) {
        List<Authors> updatedAuthors = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorsDAO authorsDAO = session.getMapper(IAuthorsDAO.class);
            try {
                authorsDAO.updateEntity(authors);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return updatedAuthors;
    }

    @Override
    public void deleteEntity(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorsDAO authorsDAO = session.getMapper(IAuthorsDAO.class);
            try {
                authorsDAO.deleteEntity(id);
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
    public List<Authors> getAll() {
        List<Authors> list = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorsDAO authorsDAO = session.getMapper(IAuthorsDAO.class);
            try {
                authorsDAO.getAll();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return list;
        }
    }
}
