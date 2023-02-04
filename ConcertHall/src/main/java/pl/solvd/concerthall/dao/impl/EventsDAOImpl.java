package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IEventsDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsEntity;
import pl.solvd.concerthall.entities.EventsEntity;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EventsDAOImpl extends MySqlDAO implements IEventsDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static Connection connection = instance.getConnection();

    private static final String GET_ALL_EVENTS_QUERY = "SELECT * FROM events";
    private static final String GET_EVENTS_QUERY = "SELECT * FROM events WHERE id = ?";
    private static final String INSERT_EVENTS_QUERY = "INSERT INTO events (category) VALUES(?)";
    private static final String UPDATE_EVENTS_QUERY = "UPDATE events SET category = ? WHERE id = ?";
    private static final String DELETE_EVENTS_QUERY = "DELETE FROM events WHERE id = ?";

    @Override
    public EventsEntity saveEntity(EventsEntity entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_EVENTS_QUERY)) {
            ps.setString(1, entity.getCategory());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<EventsEntity> getAllEvents() throws Exception {
        List<EventsEntity> events = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_EVENTS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String category = rs.getString("category");
                    events.add(new EventsEntity(id, category));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool.close();
            }
            return events;
        }
    }

    @Override
    public List<EventsEntity> getAllEventsBy(Predicate<EventsEntity> predicate) throws Exception {
        List<EventsEntity> eventsList = getAllEvents();
        eventsList = eventsList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return eventsList;
    }

    @Override
    public void getEntityById(Long id) throws Exception {
        EventsEntity events = new EventsEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_EVENTS_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    events.setId(rs.getLong(1));
                    events.setCategory(rs.getString(2));
                    System.out.println(events.getId() + "," + events.getCategory());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }

    @Override
    public List<EventsEntity> updateEntity(EventsEntity entity) throws Exception {
        List<EventsEntity> updatedEvents = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_EVENTS_QUERY)) {
            ps.setString(1, entity.getCategory());
            ps.setLong(2, entity.getId());
            ps.executeUpdate();
            updatedEvents = getAllEvents();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedEvents;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_EVENTS_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }
}
