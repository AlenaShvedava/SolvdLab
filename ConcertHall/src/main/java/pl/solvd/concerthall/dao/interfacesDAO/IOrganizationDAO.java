package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.Organization;

import java.util.List;
import java.util.function.Predicate;

public interface IOrganizationDAO extends IBaseDAO <Organization, Long> {
    List <Organization> getAllOrganizationBy (Predicate<Organization> predicate) throws Exception;
    Organization getEntityById(Long id) throws Exception;
}
