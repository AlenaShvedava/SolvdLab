package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.OrganizationEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IOrganizationDAO extends IBaseDAO <OrganizationEntity, Long> {
    List<OrganizationEntity> getAllOrganization() throws Exception;
    List <OrganizationEntity> getAllOrganizationBy (Predicate<OrganizationEntity> predicate) throws Exception;
}
