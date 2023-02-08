package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IAuthorsHasAuthorTypesDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsHasAuthorTypes;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AuthorsHasAuthorTypesDAOImpl extends MySqlDAO implements IAuthorsHasAuthorTypesDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();
    private static final String GET_ALL_AUTHORS_HAS_AUTHOR_TYPES_QUERY = "SELECT * FROM authors_has_author_types";
    private static final String GET_AUTHORS_HAS_AUTHOR_TYPES_QUERY = "SELECT authors_id, author_types_id FROM authors_has_author_types WHERE authors_id = ? AND author_types_id = ?";
    private static final String INSERT_AUTHORS_HAS_AUTHOR_TYPES_QUERY = "INSERT INTO authors_has_author_types(authors_id, author_types_id) VALUES(?, ?)";
    private static final String UPDATE_AUTHORS_HAS_AUTHOR_TYPES_QUERY = "UPDATE authors_has_author_types SET author_id = ?, author_types_id = ?";
    private static final String DELETE_AUTHORS_HAS_AUTHOR_TYPES_QUERY = "DELETE FROM authors_has_author_types WHERE authors_id = ? AND author_types_id = ?";

    @Override
    public AuthorsHasAuthorTypes addEntity(AuthorsHasAuthorTypes entity) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_AUTHORS_HAS_AUTHOR_TYPES_QUERY)) {
            ps.setLong(1, entity.getAuthorsId());
            ps.setLong(2, entity.getAuthorTypesId());
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
    public void getEntityByAuthorsIdAndAuthorTypesId(Long authorsId, Long authorTypesId) {
        AuthorsHasAuthorTypes authorsHasAuthorTypes = new AuthorsHasAuthorTypes();
        try (PreparedStatement ps = connection.prepareStatement(GET_AUTHORS_HAS_AUTHOR_TYPES_QUERY)) {
            ps.setLong(1, authorsId);
            ps.setLong(2, authorTypesId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    authorsHasAuthorTypes.setAuthorsId(rs.getLong(1));
                    authorsHasAuthorTypes.setAuthorTypesId(rs.getLong(2));
                    System.out.println(authorsHasAuthorTypes.getAuthorsId() + "," + authorsHasAuthorTypes.getAuthorTypesId());
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
    public List<AuthorsHasAuthorTypes> updateEntity(AuthorsHasAuthorTypes entity) throws Exception {
        List<AuthorsHasAuthorTypes> updatedAuthors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_AUTHORS_HAS_AUTHOR_TYPES_QUERY)) {
            ps.setLong(1, entity.getAuthorsId());
            ps.setLong(2, entity.getAuthorTypesId());
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
    public void deleteEntity(Long authorsId) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_AUTHORS_HAS_AUTHOR_TYPES_QUERY)) {
            ps.setLong(1, authorsId);
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
    public List<AuthorsHasAuthorTypes> getAll() throws Exception {
        List<AuthorsHasAuthorTypes> authorsHasAuthorTypes = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_AUTHORS_HAS_AUTHOR_TYPES_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long authorsId = rs.getLong("authors_id");
                    Long authorTypeId = rs.getLong("author_type_id");
                    authorsHasAuthorTypes.add(new AuthorsHasAuthorTypes(authorsId, authorTypeId));
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
            return authorsHasAuthorTypes;
        }
    }

    @Override
    public List<AuthorsHasAuthorTypes> getAllAuthorsHasAuthorTypesBy(Predicate<AuthorsHasAuthorTypes> predicate) throws Exception {
        List<AuthorsHasAuthorTypes> authorsList = getAll();
        authorsList = authorsList.stream().filter(predicate).collect(Collectors.toList());
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return authorsList;
    }
}
