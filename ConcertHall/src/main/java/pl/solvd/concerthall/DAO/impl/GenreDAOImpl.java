package pl.solvd.concerthall.DAO.impl;

import pl.solvd.concerthall.DAO.interfacesDAO.IGenreDAO;
import pl.solvd.concerthall.binary.Events;
import pl.solvd.concerthall.binary.Genre;
import pl.solvd.concerthall.binary.Program;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GenreDAOImpl implements IGenreDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();
    private static final String GET_ALL_GENRE_QUERY = "SELECT * FROM genre";
    private static final String GET_GENRE_QUERY = "SELECT * FROM genre WHERE id = ?";
    private static final String INSERT_GENRE_QUERY = "INSERT INTO genre(type) VALUES(?)";
    private static final String UPDATE_GENRE_QUERY = "UPDATE genre SET type = ? WHERE id = ?";
    private static final String DELETE_GENRE_QUERY = "DELETE FROM genre WHERE id = ?";
    private static final String GET_PROGRAMS_BY_GENRE_ID_QUERY = "SELECT program.title, program.description, program.age_limit, program.base_price FROM genre JOIN program_has_genre ON genre.id = program_has_genre.genre_id JOIN program ON program_has_genre.program_id = program.id WHERE genre.id = ?";
    private static final String GET_EVENTS_BY_GENRE_ID_QUERY = "SELECT events.category FROM genre JOIN events_has_genre ON genre.id = JOIN events_has_genre.genre_id JOIN events ON JOIN events_has_genre.events_id = events.id WHERE genre.id = ?";

    @Override
    public Genre addEntity(Genre entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_GENRE_QUERY)) {
            ps.setString(1, entity.getType());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> genre = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_GENRE_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String type = rs.getString("type");
                    genre.add(new Genre(id, type));
                }
            } finally {
                ConnectionPool.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return genre;
    }

    @Override
    public List<Genre> getAllGenreBy(Predicate<Genre> predicate) {
        List<Genre> genreList = getAll();
        genreList = genreList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return genreList;
    }

    @Override
    public List<Program> findProgramsByGenreId(Long genreId) {
        List<Program> programByGenre = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_PROGRAMS_BY_GENRE_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String programTitle = rs.getString("title");
                    String programDescription = rs.getString("description");
                    Long programOrganizationId = rs.getLong("organization_id");
                    String programAgeLimit = rs.getString("age_limit");
                    double programBasePrice = rs.getDouble("base_price");
                    programByGenre.add(new Program(Program.getId(), programTitle, programDescription, programOrganizationId, programAgeLimit, programBasePrice));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return programByGenre;
    }

    @Override
    public List<Events> findEventsByGenreId(Long genreId) {
        List<Events> eventsByGenre = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_EVENTS_BY_GENRE_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String eventCategory = rs.getString("category");
                    eventsByGenre.add(new Events(Events.getId(), eventCategory));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return eventsByGenre;
    }

    @Override
    public Genre getEntityById(Long id) {
        Genre genre = new Genre();
        try (PreparedStatement ps = connection.prepareStatement(GET_GENRE_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    genre.setId(rs.getLong(1));
                    genre.setType(rs.getString(2));
                    System.out.println(Genre.getId() + "," + genre.getType());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return genre;
    }

    @Override
    public List<Genre> updateEntity(Genre entity) {
        List<Genre> updatedGenre = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_GENRE_QUERY)) {
            ps.setString(1, entity.getType());
            ps.setLong(2, Genre.getId());
            ps.executeUpdate();
            updatedGenre = getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedGenre;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_GENRE_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }
}
