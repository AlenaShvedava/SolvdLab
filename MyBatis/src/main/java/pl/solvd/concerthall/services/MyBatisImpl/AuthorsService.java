package pl.solvd.concerthall.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pl.solvd.concerthall.binary.Authors;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.services.interfaces.IAuthorsService;
import pl.solvd.concerthall.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static pl.solvd.concerthall.App.LOG;

public class AuthorsService extends MySqlDAO implements IAuthorsService {
        private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();
    @Override
    public Authors addEntity(Authors authors) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorsService authorsService = session.getMapper(IAuthorsService.class);
            try {
                authorsService.addEntity(authors);
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
            IAuthorsService authorsService = session.getMapper(IAuthorsService.class);
            author = authorsService.getEntityById(authorsId);
        }
        return author;
    }

    @Override
    public void findAuthorsByAuthorTypesId(Long authorTypesId) {
        List<Authors> authorsList = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorsService authorsService = session.getMapper(IAuthorsService.class);
            authorsService.findAuthorsByAuthorTypesId(authorTypesId);
        }
    }

    @Override
    public void findAuthorsByCompositionId(Long compositionId) {
        List<Authors> authorsList = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorsService authorsService = session.getMapper(IAuthorsService.class);
            authorsService.findAuthorsByCompositionId(compositionId);
        }
    }

    @Override
    public List<Authors> updateEntity(Authors authors) {
        List<Authors> updatedAuthors = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorsService authorsService = session.getMapper(IAuthorsService.class);
            try {
                authorsService.updateEntity(authors);
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
            IAuthorsService authorsService = session.getMapper(IAuthorsService.class);
            try {
                authorsService.deleteEntity(id);
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
            IAuthorsService authorsService = session.getMapper(IAuthorsService.class);
            authorsService.getAll();
            return list;
        }
    }
}
