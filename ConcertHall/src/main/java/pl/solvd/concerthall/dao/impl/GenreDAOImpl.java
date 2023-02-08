package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IGenreDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Genre;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GenreDAOImpl extends MySqlDAO implements IGenreDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();
    private static final String GET_ALL_GENRE_QUERY = "SELECT * FROM genre";
    private static final String GET_GENRE_QUERY = "SELECT * FROM genre WHERE id = ?";
    private static final String INSERT_GENRE_QUERY = "INSERT INTO genre(type) VALUES(?)";
    private static final String UPDATE_GENRE_QUERY = "UPDATE genre SET type = ? WHERE id = ?";
    private static final String DELETE_GENRE_QUERY = "DELETE FROM genre WHERE id = ?";
    private static final String GET_GENRE_BY_PROGRAM_ID_QUERY = "SELECT genre.type FROM genre JOIN program_has_genre ON genre_id = genre.id WHERE program_id = ?";

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
    public List<Genre> getAll() throws Exception {
        List<Genre> genre = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_GENRE_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String type = rs.getString("type");
                    genre.add(new Genre(id, type));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool.close();
            }
            return genre;
        }
    }

    @Override
    public List<Genre> getAllGenreBy(Predicate<Genre> predicate) throws Exception {
        List<Genre> genreList = getAll();
        genreList = genreList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return genreList;
    }

    @Override
    public List<Genre> findGenreByProgramId(Long programId) throws SQLException {
        List<Genre> genreByProgram = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_GENRE_BY_PROGRAM_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String genreType = rs.getString("type");
                    genreByProgram.add(new Genre(Genre.getId(), genreType));
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
            return genreByProgram;
        }
    }

    @Override
    public Genre getEntityById(Long id) throws Exception {
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
    public List<Genre> updateEntity(Genre entity) throws Exception {
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
