package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.DAO.IBaseDAO;
import pl.solvd.concerthall.binary.Organization;
import pl.solvd.concerthall.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public interface IOrganizationDAO extends IBaseDAO<Organization, Long> {
    List<Organization> getAllOrganizationBy(Predicate<Organization> predicate) throws Exception;

    Organization getEntityById(Long id);

    List<Program> findProgramsByOrganizationId(Long organizationId);
}
