package pl.solvd.concerthalls.services.interfaces;

import pl.solvd.concerthalls.binary.Customer;

import java.util.List;
import java.util.function.Predicate;

public interface ICustomerService {
    Customer addCustomer(Customer customer);

    List<Customer> getAllCustomersBy(Predicate<Customer> predicate);

    Customer getCustomerById(Long customerId);

    List<Customer> updateCustomer(Customer customer);

    void deleteCustomer(Long id);

    List<Customer> getAll();
}
