package pl.solvd.concerthall.services.interfaces;

import pl.solvd.concerthall.binary.Organization;
import pl.solvd.concerthall.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public interface IOrganizationService {
    Organization addOrganization(Organization organization);

    List<Organization> getAllOrganizationsBy(Predicate<Organization> predicate);

    Organization getOrganizationById(Long organizationId);

    List<Program> findProgramsByOrganizationId(Long organizationId);

    List<Organization> updateOrganization(Organization organization);

    void deleteOrganization(Long id);

    List<Organization> getAll();
}
