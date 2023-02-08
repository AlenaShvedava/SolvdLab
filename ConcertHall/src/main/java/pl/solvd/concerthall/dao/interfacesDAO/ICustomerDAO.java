package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.Customer;

import java.util.List;
import java.util.function.Predicate;

public interface ICustomerDAO extends IBaseDAO<Customer, Long> {
    List<Customer> getAllCustomerBy(Predicate<Customer> predicate) throws Exception;

    Customer getEntityById(Long id) throws Exception;
}
