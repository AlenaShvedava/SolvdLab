package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IOrderDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsEntity;
import pl.solvd.concerthall.entities.OrderEntity;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OrderDAOImpl extends MySqlDAO implements IOrderDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static Connection connection = instance.getConnection();

    private static final String GET_ALL_ORDER_QUERY = "SELECT * FROM order";
    private static final String GET_ORDER_QUERY = "SELECT * FROM order WHERE id = ?";
    private static final String INSERT_ORDER_QUERY = "INSERT INTO order (customer_id, poster_id, price_level_id, number_of_tickets) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_ORDER_QUERY = "UPDATE order SET customer_id = ?, poster_id = ? price_level_id = ? number_of_tickets = ? WHERE id = ?";
    private static final String DELETE_ORDER_QUERY = "DELETE FROM order WHERE id = ?";

    @Override
    public OrderEntity saveEntity(OrderEntity entity) {
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
    public List<OrderEntity> getAllOrder() throws Exception {
        List<OrderEntity> order = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_ORDER_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    Long customerId = rs.getLong("customer_id");
                    Long posterId = rs.getLong("poster_id");
                    Long priceLevelId = rs.getLong("last_name");
                    int numberOfTickets= rs.getInt("number_of_tickets");
                    order.add(new OrderEntity(id, customerId, posterId, priceLevelId, numberOfTickets));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool.close();
            }
            return order;
        }
    }

    @Override
    public List<OrderEntity> getAllOrderBy (Predicate<OrderEntity> predicate) throws Exception {
        List<OrderEntity> orderList = getAllOrder();
        orderList = orderList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return orderList;
    }

    @Override
    public void getEntityById(Long id) throws Exception {
        OrderEntity order = new OrderEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_ORDER_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    order.setId(rs.getLong(1));
                    order.setCustomerId(rs.getLong(2));
                    order.setPosterId(rs.getLong(3));
                    order.setPriceLevelId(rs.getLong(3));
                    order.setNumberOfTickets(rs.getInt(3));
                    System.out.println(order.getId() + "," + order.getCustomerId() + "," + order.getPosterId() + "," + order.getPriceLevelId() + "," + order.getNumberOfTickets());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }

    @Override
    public List<OrderEntity> updateEntity(OrderEntity entity) throws Exception {
        List<OrderEntity> updatedOrder = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER_QUERY)) {
            ps.setLong(1, entity.getCustomerId());
            ps.setLong(2, entity.getPosterId());
            ps.setLong(2, entity.getPriceLevelId());
            ps.setInt(2, entity.getNumberOfTickets());
            ps.setLong(3, entity.getId());
            ps.executeUpdate();
            updatedOrder = getAllOrder();
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
