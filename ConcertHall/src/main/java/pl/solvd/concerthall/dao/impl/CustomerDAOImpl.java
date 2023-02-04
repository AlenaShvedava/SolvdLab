package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.ICustomerDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsEntity;
import pl.solvd.concerthall.entities.CustomerEntity;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CustomerDAOImpl extends MySqlDAO implements ICustomerDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static Connection connection = instance.getConnection();

    private static final String GET_ALL_CUSTOMER_QUERY = "SELECT * FROM customer";
    private static final String GET_CUSTOMER_QUERY = "SELECT * FROM customer WHERE id = ?";
    private static final String INSERT_CUSTOMER_QUERY = "INSERT INTO customer (first_name, last_name, email, balance) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_CUSTOMER_QUERY = "UPDATE customer SET first_name = ?, last_name = ? email = ?, balance = ? WHERE id = ?";
    private static final String DELETE_CUSTOMER_QUERY = "DELETE FROM customer WHERE id = ?";

    @Override
    public CustomerEntity saveEntity(CustomerEntity entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_CUSTOMER_QUERY)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getEmail());
            ps.setInt(4, entity.getBalance());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<CustomerEntity> getAllCustomer() throws Exception {
        List<CustomerEntity> customer = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_CUSTOMER_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");
                    int balance = rs.getInt("balance");
                    customer.add(new CustomerEntity(id, firstName, lastName, email, balance));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool.close();
            }
            return customer;
        }
    }

    @Override
    public List<CustomerEntity> getAllCustomerBy(Predicate<CustomerEntity> predicate) throws Exception {
        List<CustomerEntity> customerList = getAllCustomer();
        customerList = customerList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return customerList;
    }

    @Override
    public void getEntityById(Long id) throws Exception {
        CustomerEntity customer = new CustomerEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_CUSTOMER_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    customer.setId(rs.getLong(1));
                    customer.setFirstName(rs.getString(2));
                    customer.setLastName(rs.getString(3));
                    customer.setEmail(rs.getString(4));
                    customer.setBalance(rs.getInt(5));
                    System.out.println(customer.getId() + "," + customer.getFirstName() + "," + customer.getLastName() + "," + customer.getEmail() + "," + customer.getBalance());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }

    @Override
    public List<CustomerEntity> updateEntity(CustomerEntity entity) throws Exception {
        List<CustomerEntity> updatedCustomer = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_CUSTOMER_QUERY)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getEmail());
            ps.setInt(4, entity.getBalance());
            ps.setLong(5, entity.getId());
            ps.executeUpdate();
            updatedCustomer = getAllCustomer();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedCustomer;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_CUSTOMER_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }
}
