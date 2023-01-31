package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IAuthorsDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Authors;
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
    public Authors saveEntity(Authors entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_AUTHORS_QUERY)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
                ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<Authors> getAllAuthors() throws Exception {
        List<Authors> authors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_AUTHORS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    authors.add(new Authors((long) id, firstName, lastName));
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
    public List<Authors> getAllAuthorsBy(Predicate<Authors> predicate) throws Exception {
        List<Authors> authorsList = getAllAuthors();
        authorsList = authorsList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return authorsList;
    }

    @Override
    public void getEntityById(Long id) throws Exception {
        Authors author = new Authors();
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
        }
        finally {
                ConnectionPool.close();
        }
    }

    @Override
    public List<Authors> updateEntity(Authors entity) throws Exception {
        List<Authors> updatedAuthors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_AUTHORS_QUERY)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setLong(3, entity.getId());
            ps.executeUpdate();
            updatedAuthors = getAllAuthors();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
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
        }
        finally {
                ConnectionPool.close();
        }
    }
}
