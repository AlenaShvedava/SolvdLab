package pl.solvd.concerthall.services;

import pl.solvd.concerthall.dao.impl.OrganizationDAOImpl;
import pl.solvd.concerthall.dao.interfacesDAO.IOrganizationDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Organization;

import java.util.List;
import java.util.function.Predicate;

public class OrganizationService extends MySqlDAO implements IOrganizationDAO {
    private final IOrganizationDAO organizationDAO = new OrganizationDAOImpl();

    @Override
    public Organization addEntity(Organization organization) throws Exception {
        Organization o = new Organization();
        o.setName(organization.getName());
        Organization createdOrganization = this.organizationDAO.addEntity(o);
        return organization;
    }

    @Override
    public List<Organization> getAllOrganizationBy(Predicate<Organization> predicate) throws Exception {
        return organizationDAO.getAllOrganizationBy(predicate);
    }

    @Override
    public Organization getEntityById(Long organizationId) throws Exception {
        return organizationDAO.getEntityById(organizationId);
    }

    @Override
    public List<Organization> updateEntity(Organization organization) throws Exception {
        Organization o = new Organization();
        o.setName(organization.getName());
        o.setId(Organization.getId());
        return organizationDAO.updateEntity(o);
    }

    @Override
    public void deleteEntity(Long id) throws Exception {
        organizationDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Organization> getAll() throws Exception {
        return organizationDAO.getAll();
    }
}
