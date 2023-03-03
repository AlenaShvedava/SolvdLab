package pl.solvd.concerthall.DAO.impl;

import pl.solvd.concerthall.DAO.interfacesDAO.IOrderDAO;
import pl.solvd.concerthall.binary.Order;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OrderDAOImpl implements IOrderDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();
    private static final String GET_ALL_ORDER_QUERY = "SELECT * FROM order";
    private static final String GET_ORDER_QUERY = "SELECT * FROM order WHERE id = ?";
    private static final String INSERT_ORDER_QUERY = "INSERT INTO order (customer_id, poster_id, price_level_id, number_of_tickets) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_ORDER_QUERY = "UPDATE order SET customer_id = ?, poster_id = ? price_level_id = ? number_of_tickets = ? WHERE id = ?";
    private static final String DELETE_ORDER_QUERY = "DELETE FROM order WHERE id = ?";

    @Override
    public Order addEntity(Order entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_ORDER_QUERY)) {
            ps.setLong(1, entity.getCustomerId());
            ps.setLong(2, entity.getPosterId());
            ps.setLong(2, entity.getPriceLevelId());
            ps.setInt(2, entity.getNumberOfTickets());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<Order> getAll() {
        List<Order> order = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_ORDER_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    Long customerId = rs.getLong("customer_id");
                    Long posterId = rs.getLong("poster_id");
                    Long priceLevelId = rs.getLong("last_name");
                    int numberOfTickets= rs.getInt("number_of_tickets");
                    order.add(new Order(id, customerId, posterId, priceLevelId, numberOfTickets));
                }
            } finally {
                ConnectionPool.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
            return order;
        }

    @Override
    public List<Order> getAllOrderBy (Predicate<Order> predicate) {
        List<Order> orderList = getAll();
        orderList = orderList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return orderList;
    }

    @Override
    public Order getEntityById(Long id) {
        Order order = new Order();
        try (PreparedStatement ps = connection.prepareStatement(GET_ORDER_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    order.setId(rs.getLong(1));
                    order.setCustomerId(rs.getLong(2));
                    order.setPosterId(rs.getLong(3));
                    order.setPriceLevelId(rs.getLong(3));
                    order.setNumberOfTickets(rs.getInt(3));
                    System.out.println(Order.getId() + "," + order.getCustomerId() + "," + order.getPosterId() + "," + order.getPriceLevelId() + "," + order.getNumberOfTickets());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return order;
    }
    @Override
    public List<Order> updateEntity(Order entity) {
        List<Order> updatedOrder = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER_QUERY)) {
            ps.setLong(1, entity.getCustomerId());
            ps.setLong(2, entity.getPosterId());
            ps.setLong(2, entity.getPriceLevelId());
            ps.setInt(2, entity.getNumberOfTickets());
            ps.setLong(3, Order.getId());
            ps.executeUpdate();
            updatedOrder = getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedOrder;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_ORDER_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }
}
