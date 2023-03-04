package pl.solvd.concerthall.DAO.impl;

import pl.solvd.concerthall.DAO.interfacesDAO.IEventsDAO;
import pl.solvd.concerthall.binary.Events;
import pl.solvd.concerthall.binary.Genre;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EventsDAOImpl implements IEventsDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();
    private static final String GET_ALL_EVENTS_QUERY = "SELECT * FROM events";
    private static final String GET_EVENTS_QUERY = "SELECT * FROM events WHERE id = ?";
    private static final String INSERT_EVENTS_QUERY = "INSERT INTO events (category) VALUES(?)";
    private static final String UPDATE_EVENTS_QUERY = "UPDATE events SET category = ? WHERE id = ?";
    private static final String DELETE_EVENTS_QUERY = "DELETE FROM events WHERE id = ?";
    private static final String GET_GENRES_BY_EVENT_ID_QUERY = "SELECT genre.type FROM events JOIN events_has_genre ON events.id = events_has_genre.events_id JOIN genre ON events_has_genre.genre_id = genre.id WHERE events.id = ?";

    @Override
    public Events addEntity(Events entity) {
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
    public List<Events> getAll() {
        List<Events> events = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_EVENTS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String category = rs.getString("category");
                    events.add(new Events(id, category));
                }
            } finally {
                ConnectionPool.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return events;
    }

    @Override
    public List<Events> getAllEventsBy(Predicate<Events> predicate) {
        List<Events> eventsList = getAll();
        eventsList = eventsList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return eventsList;
    }

    @Override
    public List<Genre> findGenresByEventId(Long eventId) {
        List<Genre> genreByProgram = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_GENRES_BY_EVENT_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String genreType = rs.getString("type");
                    genreByProgram.add(new Genre(Genre.getId(), genreType));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return genreByProgram;
    }

    @Override
    public Events getEntityById(Long id) {
        Events events = new Events();
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
        return events;
    }

    @Override
    public List<Events> updateEntity(Events entity) {
        List<Events> updatedEvents = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_EVENTS_QUERY)) {
            ps.setString(1, entity.getCategory());
            ps.setLong(2, entity.getId());
            ps.executeUpdate();
            updatedEvents = getAll();
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
