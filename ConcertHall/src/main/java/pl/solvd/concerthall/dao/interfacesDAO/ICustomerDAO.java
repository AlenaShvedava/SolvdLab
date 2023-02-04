package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.CustomerEntity;

import java.util.List;
import java.util.function.Predicate;

public interface ICustomerDAO extends IBaseDAO <CustomerEntity, Long>{
    List<CustomerEntity> getAllCustomer() throws Exception;
    List <CustomerEntity> getAllCustomerBy (Predicate<CustomerEntity> predicate) throws Exception;
}
