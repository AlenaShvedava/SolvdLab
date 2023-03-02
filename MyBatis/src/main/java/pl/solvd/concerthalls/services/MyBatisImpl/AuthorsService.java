package pl.solvd.concerthalls.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthalls.DAO.interfaces.IAuthorsDAO;
import pl.solvd.concerthalls.binary.AuthorTypes;
import pl.solvd.concerthalls.binary.Authors;
import pl.solvd.concerthalls.binary.Composition;
import pl.solvd.concerthalls.services.interfaces.IAuthorsService;
import pl.solvd.concerthalls.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AuthorsService implements IAuthorsService {
    Logger LOG = LogManager.getLogger(AuthorsService.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();

    @Override
    public Authors addAuthor(Authors authors) {
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
    public Authors getAuthorById(Long authorsId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorsDAO authorsDAO = session.getMapper(IAuthorsDAO.class);
            Authors author = authorsDAO.getEntityById(authorsId);
            return author;
        }
    }

    @Override
    public List<Authors> updateAuthor(Authors authors) {
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
    public void deleteAuthor(Long id) {
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
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorsDAO authorsDAO = session.getMapper(IAuthorsDAO.class);
            List<Authors> list = authorsDAO.getAll();
            return list;
        }
    }

    @Override
    public List<AuthorTypes> findAuthorTypesByAuthorsId(Long authorsId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorsDAO authorsDAO = session.getMapper(IAuthorsDAO.class);
            List<AuthorTypes> authorTypesList = authorsDAO.findAuthorTypesByAuthorsId(authorsId);
            return authorTypesList;
        }
    }

    @Override
    public List<Composition> findCompositionsByAuthorsId(Long authorsId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorsDAO authorsDAO = session.getMapper(IAuthorsDAO.class);
            List<Composition> compositionsList = authorsDAO.findCompositionsByAuthorsId(authorsId);
            return compositionsList;
        }
    }
}
