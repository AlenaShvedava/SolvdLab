package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IGenreDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsEntity;
import pl.solvd.concerthall.entities.GenreEntity;
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
    private static Connection connection = instance.getConnection();

    private static final String GET_ALL_GENRE_QUERY = "SELECT * FROM genre";
    private static final String GET_GENRE_QUERY = "SELECT * FROM genre WHERE id = ?";
    private static final String INSERT_GENRE_QUERY = "INSERT INTO genre(type) VALUES(?)";
    private static final String UPDATE_GENRE_QUERY = "UPDATE genre SET type = ? WHERE id = ?";
    private static final String DELETE_GENRE_QUERY = "DELETE FROM genre WHERE id = ?";

    @Override
    public GenreEntity saveEntity(GenreEntity entity) {
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
    public List<GenreEntity> getAllGenre() throws Exception {
        List<GenreEntity> genre = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_GENRE_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String type = rs.getString("type");
                    genre.add(new GenreEntity(id, type));
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
    public List<GenreEntity> getAllGenreBy(Predicate<GenreEntity> predicate) throws Exception {
        List<GenreEntity> genreList = getAllGenre();
        genreList = genreList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return genreList;
    }

    @Override
    public void getEntityById(Long id) throws Exception {
        GenreEntity genre = new GenreEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_GENRE_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    genre.setId(rs.getLong(1));
                    genre.setType(rs.getString(2));
                    System.out.println(genre.getId() + "," + genre.getType());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }

    @Override
    public List<GenreEntity> updateEntity(GenreEntity entity) throws Exception {
        List<GenreEntity> updatedGenre = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_GENRE_QUERY)) {
            ps.setString(1, entity.getType());
            ps.setLong(2, entity.getId());
            ps.executeUpdate();
            updatedGenre = getAllGenre();
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
