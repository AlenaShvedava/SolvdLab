package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.ITicketDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsEntity;
import pl.solvd.concerthall.entities.TicketEntity;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TicketDAOImpl extends MySqlDAO implements ITicketDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static Connection connection = instance.getConnection();

    private static final String GET_ALL_TICKET_QUERY = "SELECT * FROM ticket";
    private static final String GET_TICKET_QUERY = "SELECT * FROM ticket WHERE id = ?";
    private static final String INSERT_TICKET_QUERY = "INSERT INTO ticket (order_id, my_seat_id, price, active) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_TICKET_QUERY = "UPDATE ticket SET order_id = ?, my_seat_id = ?, price = ?, active = ? WHERE id = ?";
    private static final String DELETE_TICKET_QUERY = "DELETE FROM ticket WHERE id = ?";

    @Override
    public TicketEntity saveEntity(TicketEntity entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_TICKET_QUERY)) {
            ps.setLong(1, entity.getOrderId());
            ps.setLong(2, entity.getMySeatId());
            ps.setInt(3, entity.getPrice());
            ps.setBoolean(4, entity.isActive());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<TicketEntity> getAllTicket() throws Exception {
        List<TicketEntity> ticket = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_TICKET_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    Long orderId = rs.getLong("order_id");
                    Long mySeatId = rs.getLong("my_seat_id");
                    int price = rs.getInt("price");
                    Boolean active = rs.getBoolean("active");
                    ticket.add(new TicketEntity(id, orderId, mySeatId, price, active));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool.close();
            }
            return ticket;
        }
    }

    @Override
    public List<TicketEntity> getAllTicketBy(Predicate<TicketEntity> predicate) throws Exception {
        List<TicketEntity> ticketList = getAllTicket();
        ticketList = ticketList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return ticketList;
    }

    @Override
    public void getEntityById(Long id) throws Exception {
        TicketEntity ticket = new TicketEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_TICKET_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ticket.setId(rs.getLong(1));
                    ticket.setOrderId(rs.getLong(2));
                    ticket.setMySeatId(rs.getLong(3));
                    ticket.setPrice(rs.getInt(3));
                    ticket.setActive(rs.getBoolean(3));
                    System.out.println(ticket.getId() + "," + ticket.getOrderId() + "," + ticket.getMySeatId() + "," + ticket.getPrice() + "," + ticket.isActive());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }

    @Override
    public List<TicketEntity> updateEntity(TicketEntity entity) throws Exception {
        List<TicketEntity> updatedTicket = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_TICKET_QUERY)) {
            ps.setLong(1, entity.getOrderId());
            ps.setLong(2, entity.getMySeatId());
            ps.setInt(3, entity.getPrice());
            ps.setBoolean(4, entity.isActive());
            ps.setLong(5, entity.getId());
            ps.executeUpdate();
            updatedTicket = getAllTicket();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedTicket;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_TICKET_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }
}
