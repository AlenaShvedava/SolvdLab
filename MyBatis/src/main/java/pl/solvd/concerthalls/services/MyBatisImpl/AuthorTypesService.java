package pl.solvd.concerthalls.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthalls.DAO.interfaces.IAuthorTypesDAO;
import pl.solvd.concerthalls.binary.AuthorTypes;
import pl.solvd.concerthalls.binary.Authors;
import pl.solvd.concerthalls.services.interfaces.IAuthorTypeService;
import pl.solvd.concerthalls.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AuthorTypesService implements IAuthorTypeService {
    Logger LOG = LogManager.getLogger(AuthorTypesService.class);
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();

    @Override
    public AuthorTypes addAuthorType(AuthorTypes authorTypes) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorTypesDAO authorTypeDAO = session.getMapper(IAuthorTypesDAO.class);
            try {
                authorTypeDAO.addEntity(authorTypes);
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
    public AuthorTypes getAuthorTypeById(Long authorTypesId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorTypesDAO authorTypeDAO = session.getMapper(IAuthorTypesDAO.class);
            AuthorTypes authorTypes = authorTypeDAO.getEntityById(authorTypesId);
            return authorTypes;
        }
    }

    @Override
    public List<AuthorTypes> updateAuthorType(AuthorTypes authorTypes) {
        List<AuthorTypes> updatedAuthorTypes = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorTypesDAO authorTypeDAO = session.getMapper(IAuthorTypesDAO.class);
            try {
                authorTypeDAO.updateEntity(authorTypes);
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
    public void deleteAuthorType(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorTypesDAO authorTypesDAO = session.getMapper(IAuthorTypesDAO.class);
            try {
                authorTypesDAO.deleteEntity(id);
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
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorTypesDAO authorTypesDAO = session.getMapper(IAuthorTypesDAO.class);
            List<AuthorTypes> list = authorTypesDAO.getAll();
            return list;
        }
    }

    @Override
    public List<Authors> findAuthorsByAuthorTypesId(Long authorTypesId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IAuthorTypesDAO authorTypesDAO = session.getMapper(IAuthorTypesDAO.class);
            List<Authors> authorsList = authorTypesDAO.findAuthorsByAuthorTypesId(authorTypesId);
            return authorsList;
        }
    }
}
