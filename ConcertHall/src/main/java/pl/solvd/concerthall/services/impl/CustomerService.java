package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.DAO.impl.CustomerDAOImpl;
import pl.solvd.concerthall.DAO.interfacesDAO.ICustomerDAO;
import pl.solvd.concerthall.binary.Customer;

import java.util.List;
import java.util.function.Predicate;

public class CustomerService implements ICustomerDAO {
    private final ICustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public Customer addEntity(Customer customer) {
        Customer c = new Customer();
        c.setFirstName(customer.getFirstName());
        c.setLastName(customer.getLastName());
        c.setEmail(customer.getEmail());
        c.setBalance(customer.getBalance());
        Customer createdCustomer = this.customerDAO.addEntity(c);
        return customer;
    }

    @Override
    public List<Customer> getAllCustomerBy(Predicate <Customer> predicate) {
        return customerDAO.getAllCustomerBy(predicate);
    }

    @Override
    public Customer getEntityById(Long customerId) {
        return customerDAO.getEntityById(customerId);
    }

    @Override
    public List<Customer> updateEntity(Customer customer) {
        Customer c = new Customer();
        c.setFirstName(customer.getFirstName());
        c.setLastName(customer.getLastName());
        c.setEmail(customer.getEmail());
        c.setBalance(customer.getBalance());
        c.setId(Customer.getId());
        return customerDAO.updateEntity(c);
    }

    @Override
    public void deleteEntity(Long id) {
        customerDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Customer> getAll() {
        return customerDAO.getAll();
    }
}
