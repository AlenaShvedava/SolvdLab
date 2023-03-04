package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.DAO.impl.OrganizationDAOImpl;
import pl.solvd.concerthall.DAO.interfacesDAO.IOrganizationDAO;
import pl.solvd.concerthall.binary.Organization;
import pl.solvd.concerthall.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public class OrganizationService implements IOrganizationDAO {
    private final IOrganizationDAO organizationDAO = new OrganizationDAOImpl();

    @Override
    public Organization addEntity(Organization organization) {
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
    public Organization getEntityById(Long organizationId) {
        return organizationDAO.getEntityById(organizationId);
    }

    @Override
    public List<Program> findProgramsByOrganizationId(Long organizationId) {
        return organizationDAO.findProgramsByOrganizationId(organizationId);
    }

    @Override
    public List<Organization> updateEntity(Organization organization) {
        Organization o = new Organization();
        o.setName(organization.getName());
        o.setId(Organization.getId());
        return organizationDAO.updateEntity(o);
    }

    @Override
    public void deleteEntity(Long id) {
        organizationDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Organization> getAll() {
        return organizationDAO.getAll();
    }
}
