package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.DAO.IBaseDAO;
import pl.solvd.concerthall.binary.Customer;

import java.util.List;
import java.util.function.Predicate;

public interface ICustomerDAO extends IBaseDAO<Customer, Long> {
    List<Customer> getAllCustomerBy(Predicate<Customer> predicate);

    Customer getEntityById(Long id);
}
