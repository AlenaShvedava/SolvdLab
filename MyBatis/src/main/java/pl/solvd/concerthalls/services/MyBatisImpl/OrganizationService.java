package pl.solvd.concerthalls.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthalls.DAO.interfaces.IOrganizationDAO;
import pl.solvd.concerthalls.binary.Organization;
import pl.solvd.concerthalls.binary.Program;
import pl.solvd.concerthalls.services.interfaces.IOrganizationService;
import pl.solvd.concerthalls.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class OrganizationService implements IOrganizationService {
    Logger LOG = LogManager.getLogger(OrganizationService.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();

    @Override
    public Organization addOrganization(Organization organization) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IOrganizationDAO organizationDAO = session.getMapper(IOrganizationDAO.class);
            try {
                organizationDAO.addEntity(organization);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return organization;
    }

    @Override
    public List<Organization> getAllOrganizationsBy(Predicate<Organization> predicate) {
        return null;
    }

    @Override
    public Organization getOrganizationById(Long organizationId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IOrganizationDAO organizationDAO = session.getMapper(IOrganizationDAO.class);
            Organization organization = organizationDAO.getEntityById(organizationId);
            return organization;
        }
    }
    @Override
    public List<Organization> updateOrganization(Organization organization) {
        List<Organization> updatedOrganization = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IOrganizationDAO organizationDAO = session.getMapper(IOrganizationDAO.class);
            try {
                organizationDAO.updateEntity(organization);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return updatedOrganization;
    }

    @Override
    public void deleteOrganization(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IOrganizationDAO organizationDAO = session.getMapper(IOrganizationDAO.class);
            try {
                organizationDAO.deleteEntity(id);
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
    public List<Organization> getAll() {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IOrganizationDAO organizationDAO = session.getMapper(IOrganizationDAO.class);
            List<Organization> list = organizationDAO.getAll();
            return list;
        }
    }
    @Override
    public List <Program> findProgramsByOrganizationId(Long organizationId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IOrganizationDAO organizationDAO = session.getMapper(IOrganizationDAO.class);
            List<Program> programsList = organizationDAO.findProgramsByOrganizationId(organizationId);
            return programsList;
        }
    }
}
