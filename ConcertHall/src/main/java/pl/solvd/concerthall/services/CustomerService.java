package pl.solvd.concerthall.services;

import pl.solvd.concerthall.dao.impl.CustomerDAOImpl;
import pl.solvd.concerthall.dao.interfacesDAO.ICustomerDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Customer;

import java.util.List;
import java.util.function.Predicate;

public class CustomerService extends MySqlDAO implements ICustomerDAO {
    private final ICustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public Customer addEntity(Customer customer) throws Exception {
        Customer c = new Customer();
        c.setFirstName(customer.getFirstName());
        c.setLastName(customer.getLastName());
        c.setEmail(customer.getEmail());
        c.setBalance(customer.getBalance());
        Customer createdCustomer = this.customerDAO.addEntity(c);
        return customer;
    }

    @Override
    public List<Customer> getAllCustomerBy(Predicate<Customer> predicate) throws Exception {
        return customerDAO.getAllCustomerBy(predicate);
    }

    @Override
    public Customer getEntityById(Long customerId) throws Exception {
        return customerDAO.getEntityById(customerId);
    }

    @Override
    public List<Customer> updateEntity(Customer customer) throws Exception {
        Customer c = new Customer();
        c.setFirstName(customer.getFirstName());
        c.setLastName(customer.getLastName());
        c.setEmail(customer.getEmail());
        c.setBalance(customer.getBalance());
        c.setId(Customer.getId());
        return customerDAO.updateEntity(c);
    }

    @Override
    public void deleteEntity(Long id) throws Exception {
        customerDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Customer> getAll() throws Exception {
        return customerDAO.getAll();
    }
}
