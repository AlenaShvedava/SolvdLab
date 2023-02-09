package pl.solvd.concerthall.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pl.solvd.concerthall.binary.AuthorTypes;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.services.interfaces.IAuthorTypeService;
import pl.solvd.concerthall.utils.MyBatisFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static pl.solvd.concerthall.App.LOG;

public class AuthorTypesService extends MySqlDAO implements IAuthorTypeService {
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();
    @Override
    public AuthorTypes addEntity(AuthorTypes authorTypes) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorTypeService authorTypeService = session.getMapper(IAuthorTypeService.class);
            try {
                authorTypeService.addEntity(authorTypes);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return authorTypes;
    }

    @Override
    public List<AuthorTypes> getAllAuthorTypesBy(Predicate<AuthorTypes> predicate) {
        return null;
    }

    @Override
    public AuthorTypes getEntityById(Long authorTypesId) {
        AuthorTypes authorTypes;
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorTypeService authorTypesService = session.getMapper(IAuthorTypeService.class);
            authorTypes = authorTypesService.getEntityById(authorTypesId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return authorTypes;
    }

    @Override
    public void findAuthorsTypesByAuthorsId(Long authorsId) {
        List<AuthorTypes> authorTypesList = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorTypeService authorTypesService = session.getMapper(IAuthorTypeService.class);
            try {
                authorTypesService.findAuthorsTypesByAuthorsId(authorsId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public List<AuthorTypes> updateEntity(AuthorTypes authorTypes) {
        List<AuthorTypes> updatedAuthorTypes = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorTypeService authorTypeService = session.getMapper(IAuthorTypeService.class);
            try {
                authorTypeService.updateEntity(authorTypes);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return updatedAuthorTypes;
    }

    @Override
    public void deleteEntity(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorTypeService authorTypesService = session.getMapper(IAuthorTypeService.class);
            try {
                authorTypesService.deleteEntity(id);
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
    public List<AuthorTypes> getAll() {
        List<AuthorTypes> list = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorTypeService authorTypesService = session.getMapper(IAuthorTypeService.class);
            authorTypesService.getAll();
            return list;
        }
    }
}
