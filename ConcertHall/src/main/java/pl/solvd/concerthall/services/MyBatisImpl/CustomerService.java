package pl.solvd.concerthall.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthall.DAO.interfacesDAO.ICustomerDAO;
import pl.solvd.concerthall.binary.Customer;
import pl.solvd.concerthall.services.interfaces.ICustomerService;
import pl.solvd.concerthall.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CustomerService implements ICustomerService {
    Logger LOG = LogManager.getLogger(CustomerService.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();

    @Override
    public Customer addCustomer(Customer customer) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            ICustomerDAO customerDAO = session.getMapper(ICustomerDAO.class);
            try {
                customerDAO.addEntity(customer);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomersBy(Predicate<Customer> predicate) {
        return null;
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            ICustomerDAO customerDAO = session.getMapper(ICustomerDAO.class);
            Customer customer = customerDAO.getEntityById(customerId);
            return customer;
        }
    }

    @Override
    public List<Customer> updateCustomer(Customer customer) {
        List<Customer> updatedCustomer = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            ICustomerDAO customerDAO = session.getMapper(ICustomerDAO.class);
            try {
                customerDAO.updateEntity(customer);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return updatedCustomer;
    }

    @Override
    public void deleteCustomer(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            ICustomerDAO customerDAO = session.getMapper(ICustomerDAO.class);
            try {
                customerDAO.deleteEntity(id);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Customer> getAll() {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            ICustomerDAO customerDAO = session.getMapper(ICustomerDAO.class);
            List<Customer> list = customerDAO.getAll();
            return list;
        }
    }
}
