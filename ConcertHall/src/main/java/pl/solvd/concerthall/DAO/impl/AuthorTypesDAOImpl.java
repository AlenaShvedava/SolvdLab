package pl.solvd.concerthall.DAO.impl;

import pl.solvd.concerthall.DAO.interfacesDAO.IAuthorTypesDAO;
import pl.solvd.concerthall.binary.AuthorTypes;
import pl.solvd.concerthall.binary.Authors;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AuthorTypesDAOImpl implements IAuthorTypesDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();
    private static final String GET_ALL_AUTHOR_TYPES_QUERY = "SELECT * FROM author_types";
    private static final String GET_AUTHOR_TYPES_QUERY = "SELECT * FROM author_types WHERE id = ?";
    private static final String INSERT_AUTHOR_TYPES_QUERY = "INSERT INTO author_types(type) VALUES(?)";
    private static final String UPDATE_AUTHOR_TYPES_QUERY = "UPDATE author_types SET type = ?, WHERE id = ?";
    private static final String DELETE_AUTHOR_TYPES_QUERY = "DELETE FROM author_types WHERE id = ?";
    private static final String GET_AUTHORS_BY_AUTHOR_TYPES_ID_QUERY = "SELECT authors.first_name, authors.last_name FROM author_types JOIN authors_has_author_types ON author_types.id = authors_has_author_types.author_types_id JOIN authors ON authors_has_author_types.authors_id = authors.id WHERE author_types.id = ?";

    @Override
    public AuthorTypes addEntity(AuthorTypes entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_AUTHOR_TYPES_QUERY)) {
            ps.setString(1, entity.getType());
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
    public List<AuthorTypes> getAll() {
        List<AuthorTypes> authorTypes = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_AUTHOR_TYPES_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String type = rs.getString("type");
                    authorTypes.add(new AuthorTypes(id, type));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return authorTypes;
    }

    @Override
    public List<AuthorTypes> getAllAuthorTypesBy(Predicate<AuthorTypes> predicate) {
        List<AuthorTypes> authorsList = getAll();
        authorsList = authorsList.stream().filter(predicate).collect(Collectors.toList());
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return authorsList;
    }

    @Override
    public AuthorTypes getEntityById(Long id) {
        AuthorTypes authorTypes = new AuthorTypes();
        try (PreparedStatement ps = connection.prepareStatement(GET_AUTHOR_TYPES_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    authorTypes.setId(rs.getLong(1));
                    authorTypes.setType(rs.getString(2));
                    System.out.println(AuthorTypes.getId() + "," + authorTypes.getType());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return authorTypes;
    }

    @Override
    public List<AuthorTypes> updateEntity(AuthorTypes entity) {
        List<AuthorTypes> updatedAuthors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_AUTHOR_TYPES_QUERY)) {
            ps.setString(1, entity.getType());
            ps.setLong(2, AuthorTypes.getId());
            ps.executeUpdate();
            updatedAuthors = getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return updatedAuthors;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_AUTHOR_TYPES_QUERY)) {
            ps.setLong(1, id);
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
    public List<Authors> findAuthorsByAuthorTypesId(Long authorsId) {
        List<Authors> authorsByAuthorTypes = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_AUTHORS_BY_AUTHOR_TYPES_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    authorsByAuthorTypes.add(new Authors(firstName, lastName));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return authorsByAuthorTypes;
    }
}
