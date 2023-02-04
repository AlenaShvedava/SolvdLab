package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IAuthorsDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsEntity;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AuthorsDAOImpl extends MySqlDAO implements IAuthorsDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static Connection connection = instance.getConnection();

    private static final String GET_ALL_AUTHORS_QUERY = "SELECT * FROM authors";
    private static final String GET_AUTHORS_QUERY = "SELECT * FROM authors WHERE id = ?";
    private static final String INSERT_AUTHORS_QUERY = "INSERT INTO authors(first_name, last_name) VALUES(?, ?)";
    private static final String UPDATE_AUTHORS_QUERY = "UPDATE authors SET first_name = ?, last_name = ? WHERE id = ?";
    private static final String DELETE_AUTHORS_QUERY = "DELETE FROM authors WHERE id = ?";

    @Override
    public AuthorsEntity saveEntity(AuthorsEntity entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_AUTHORS_QUERY)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<AuthorsEntity> getAllAuthors() throws Exception {
        List<AuthorsEntity> authors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_AUTHORS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    authors.add(new AuthorsEntity((long) id, firstName, lastName));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool.close();
            }
            return authors;
        }
    }

    @Override
    public List<AuthorsEntity> getAllAuthorsBy(Predicate<AuthorsEntity> predicate) throws Exception {
        List<AuthorsEntity> authorsList = getAllAuthors();
        authorsList = authorsList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return authorsList;
    }

    @Override
    public void getEntityById(Long id) throws Exception {
        AuthorsEntity author = new AuthorsEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_AUTHORS_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    author.setId(rs.getLong(1));
                    author.setFirstName(rs.getString(2));
                    author.setLastName(rs.getString(3));
                    System.out.println(author.getId() + "," + author.getFirstName() + "," + author.getLastName());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }

    @Override
    public List<AuthorsEntity> updateEntity(AuthorsEntity entity) throws Exception {
        List<AuthorsEntity> updatedAuthors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_AUTHORS_QUERY)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setLong(3, entity.getId());
            ps.executeUpdate();
            updatedAuthors = getAllAuthors();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedAuthors;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_AUTHORS_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }
}
