package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IEventsHasGenreDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsEntity;
import pl.solvd.concerthall.entities.AuthorsHasAuthorTypesEntity;
import pl.solvd.concerthall.entities.EventsHasGenreEntity;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EventsHasGenreDAOImpl extends MySqlDAO implements IEventsHasGenreDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static Connection connection = instance.getConnection();
    private static final String GET_ALL_EVENTS_HAS_GENRE_QUERY = "SELECT * FROM events_has_genre";
    private static final String GET_EVENTS_HAS_GENRE_QUERY = "SELECT * FROM events_has_genre WHERE events_id = ?";
    private static final String INSERT_EVENTS_HAS_GENRE_QUERY = "INSERT INTO events_has_genre (events_id, genre_id) VALUES(?, ?)";
    private static final String UPDATE_EVENTS_HAS_GENRE_QUERY = "UPDATE events_has_genre SET genre_id = ? WHERE events_id = ?";
    private static final String DELETE_EVENTS_HAS_GENRE_QUERY = "DELETE FROM events_has_genre WHERE events_id = ?";

    @Override
    public EventsHasGenreEntity saveEntity(EventsHasGenreEntity entity) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_EVENTS_HAS_GENRE_QUERY)) {
            ps.setLong(1, entity.getEventsId());
            ps.setLong(2, entity.getGenreId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return entity;
    }

    @Override
    public void getEntityById(Long eventsId) throws Exception {
        EventsHasGenreEntity eventsHasGenre = new EventsHasGenreEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_EVENTS_HAS_GENRE_QUERY)) {
            ps.setLong(1, eventsId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    eventsHasGenre.setEventsId(rs.getLong(1));
                    eventsHasGenre.setGenreId(rs.getLong(2));
                    System.out.println(eventsHasGenre.getEventsId() + "," + eventsHasGenre.getGenreId());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public List<EventsHasGenreEntity> updateEntity(EventsHasGenreEntity entity) throws Exception {
        List<EventsHasGenreEntity> updatedEventsHasGenre = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_EVENTS_HAS_GENRE_QUERY)) {
            ps.setLong(1, entity.getEventsId());
            ps.setLong(2, entity.getGenreId());
            ps.executeUpdate();
            updatedEventsHasGenre = getAllEventsHasGenre();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return updatedEventsHasGenre;
    }

    @Override
    public void deleteEntity(Long eventsId) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_EVENTS_HAS_GENRE_QUERY)) {
            ps.setLong(1, eventsId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public List<EventsHasGenreEntity> getAllEventsHasGenre() throws Exception {
        List<AuthorsHasAuthorTypesEntity> authorsHasAuthorTypes = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_EVENTS_HAS_GENRE_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long eventsId = rs.getLong("events_id");
                    Long genreId = rs.getLong("genre_id");
                    authorsHasAuthorTypes.add(new AuthorsHasAuthorTypesEntity(eventsId, genreId));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            return getAllEventsHasGenre();
        }
    }

    @Override
    public List<EventsHasGenreEntity> getAllEventsHasGenreBy(Predicate<EventsHasGenreEntity> predicate) throws Exception {
        List<EventsHasGenreEntity> eventsList = getAllEventsHasGenre();
        eventsList = eventsList.stream().filter(predicate).collect(Collectors.toList());
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return eventsList;
    }
}
