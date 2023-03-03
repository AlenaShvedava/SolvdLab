package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthalls.DAO.IBaseDAO;
import pl.solvd.concerthalls.binary.Organization;
import pl.solvd.concerthalls.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public interface IOrganizationDAO extends IBaseDAO<Organization, Long> {
    List<Organization> getAllOrganizationBy(Predicate<Organization> predicate) throws Exception;

    Organization getEntityById(Long id);

    List<Program> findProgramsByOrganizationId(Long organizationId);
}
