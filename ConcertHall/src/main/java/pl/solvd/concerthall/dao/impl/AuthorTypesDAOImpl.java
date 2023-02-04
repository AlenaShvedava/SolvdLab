package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IAuthorTypesDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorTypesEntity;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AuthorTypesDAOImpl extends MySqlDAO implements IAuthorTypesDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static Connection connection = instance.getConnection();
    private static final String GET_ALL_AUTHOR_TYPES_QUERY = "SELECT * FROM author_types";
    private static final String GET_AUTHOR_TYPES_QUERY = "SELECT * FROM author_types WHERE id = ?";
    private static final String INSERT_AUTHOR_TYPES_QUERY = "INSERT INTO author_types(type) VALUES(?)";
    private static final String UPDATE_AUTHOR_TYPES_QUERY = "UPDATE author_types SET type = ?, WHERE id = ?";
    private static final String DELETE_AUTHOR_TYPES_QUERY = "DELETE FROM author_types WHERE id = ?";

    @Override
    public AuthorTypesEntity saveEntity(AuthorTypesEntity entity) throws Exception {
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
    public List<AuthorTypesEntity> getAllAuthorTypes() throws Exception {
        List<AuthorTypesEntity> authorTypes = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_AUTHOR_TYPES_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String type = rs.getString("type");
                    authorTypes.add(new AuthorTypesEntity((long) id, type));
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
            return authorTypes;
        }
    }

    @Override
    public List<AuthorTypesEntity> getAllAuthorTypesBy(Predicate<AuthorTypesEntity> predicate) throws Exception {
        List<AuthorTypesEntity> authorsList = getAllAuthorTypes();
        authorsList = authorsList.stream().filter(predicate).collect(Collectors.toList());
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return authorsList;
    }

    @Override
    public void getEntityById(Long id) throws Exception {
        AuthorTypesEntity authorTypes = new AuthorTypesEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_AUTHOR_TYPES_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    authorTypes.setId(rs.getLong(1));
                    authorTypes.setType(rs.getString(2));
                    System.out.println(authorTypes.getId() + "," + authorTypes.getType());
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
    public List<AuthorTypesEntity> updateEntity(AuthorTypesEntity entity) throws Exception {
        List<AuthorTypesEntity> updatedAuthors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_AUTHOR_TYPES_QUERY)) {
            ps.setString(1, entity.getType());
            ps.setLong(2, entity.getId());
            ps.executeUpdate();
            updatedAuthors = getAllAuthorTypes();
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
    public void deleteEntity(Long id) throws Exception {
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
}
