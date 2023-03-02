package pl.solvd.concerthalls.DAO.interfaces;

import pl.solvd.concerthalls.binary.Customer;
import pl.solvd.concerthalls.DAO.IBaseDAO;

import java.util.List;
import java.util.function.Predicate;

public interface ICustomerDAO extends IBaseDAO<Customer, Long> {
    List<Customer> getAllCustomerBy(Predicate<Customer> predicate);

    Customer getEntityById(Long id);
}
