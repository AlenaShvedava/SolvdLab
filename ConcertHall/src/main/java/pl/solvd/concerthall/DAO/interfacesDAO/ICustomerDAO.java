package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthalls.DAO.IBaseDAO;
import pl.solvd.concerthalls.binary.Customer;

import java.util.List;
import java.util.function.Predicate;

public interface ICustomerDAO extends IBaseDAO<Customer, Long> {
    List<Customer> getAllCustomerBy(Predicate<Customer> predicate);

    Customer getEntityById(Long id);
}
