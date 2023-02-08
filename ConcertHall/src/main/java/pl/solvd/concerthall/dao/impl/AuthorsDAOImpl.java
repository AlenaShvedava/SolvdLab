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
    private static final Connection connection = instance.getConnection();
    private static final String GET_ALL_AUTHORS_QUERY = "SELECT * FROM authors";
    private static final String GET_AUTHOR_QUERY = "SELECT * FROM authors WHERE id = ?";
    private static final String INSERT_AUTHOR_QUERY = "INSERT INTO authors(first_name, last_name) VALUES(?, ?)";
    private static final String UPDATE_AUTHOR_QUERY = "UPDATE authors SET first_name = ?, last_name = ? WHERE id = ?";
    private static final String DELETE_AUTHOR_QUERY = "DELETE FROM authors WHERE id = ?";
    private static final String GET_AUTHORS_BY_AUTHOR_TYPES_ID_QUERY = "SELECT authors.first_name, authors.last_name FROM authors JOIN authors_has_author_types ON authors_id = authors.id where author_types_id = ?";
    private static final String GET_AUTHORS_BY_COMPOSITION_ID_QUERY = "SELECT authors.first_name, authors.last_name FROM authors JOIN composition_has_authors ON authors_id = authors.id where composition_id = ?";


    @Override
    public Authors addEntity(Authors entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_AUTHOR_QUERY)) {
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
    public List<Authors> getAll() throws Exception {
        List<Authors> authors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_AUTHORS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    authors.add(new Authors(id, firstName, lastName));
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
        List<Authors> authorsList = getAll();
        authorsList = authorsList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return authorsList;
    }

    @Override
    public Authors getEntityById(Long id) throws Exception {
        Authors author = new Authors();
        try (PreparedStatement ps = connection.prepareStatement(GET_AUTHOR_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    author.setId(rs.getLong(1));
                    author.setFirstName(rs.getString(2));
                    author.setLastName(rs.getString(3));
                    System.out.println(Authors.getId() + "," + author.getFirstName() + "," + author.getLastName());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return null;
    }

    @Override
    public List<Authors> updateEntity(Authors entity) throws Exception {
        List<Authors> updatedAuthors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_AUTHOR_QUERY)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setLong(3, Authors.getId());
            ps.executeUpdate();
            updatedAuthors = getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedAuthors;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_AUTHOR_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }

    @Override
    public List<Authors> findAuthorsByAuthorTypesId(Long authorTypesId) throws SQLException {
        List<Authors> authorsByAuthorType = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_AUTHORS_BY_AUTHOR_TYPES_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String authorsFirstName = rs.getString("first_name");
                    String authorsLastName = rs.getString("last_name");
                    authorsByAuthorType.add(new Authors(Authors.getId(),authorsFirstName, authorsLastName));
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
            return authorsByAuthorType;
        }
    }

    @Override
    public List<Authors> findAuthorsByCompositionId(Long compositionId) throws SQLException {
        List<Authors> authorsByComposition = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_AUTHORS_BY_COMPOSITION_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String authorsFirstName = rs.getString("first_name");
                    String authorsLastName = rs.getString("last_name");
                    authorsByComposition.add(new Authors(Authors.getId(), authorsFirstName, authorsLastName));
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
            return authorsByComposition;
        }
    }
}
