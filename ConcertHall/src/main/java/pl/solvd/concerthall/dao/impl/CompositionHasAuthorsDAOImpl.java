package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.ICompositionHasAuthorsDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsEntity;
import pl.solvd.concerthall.entities.CompositionEntity;
import pl.solvd.concerthall.entities.CompositionHasAuthorsEntity;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CompositionHasAuthorsDAOImpl extends MySqlDAO implements ICompositionHasAuthorsDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static Connection connection = instance.getConnection();

    private static final String GET_ALL_COMPOSITION_HAS_AUTHORS_QUERY = "SELECT * FROM composition_has_authors";
    private static final String GET_COMPOSITION_HAS_AUTHORS_QUERY = "SELECT * FROM composition_has_authors WHERE composition_id = ?";
    private static final String INSERT_COMPOSITION_HAS_AUTHORS_QUERY = "INSERT INTO composition_has_authors (composition_id, authors_id) VALUES(?, ?)";
    private static final String UPDATE_COMPOSITION_HAS_AUTHORS_QUERY = "UPDATE composition_has_authors SET authors_id= ? WHERE composition_id = ?";
    private static final String DELETE_COMPOSITION_HAS_AUTHORS_QUERY = "DELETE FROM composition_has_authors WHERE composition_id = ?";

    @Override
    public CompositionHasAuthorsEntity saveEntity(CompositionHasAuthorsEntity entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_COMPOSITION_HAS_AUTHORS_QUERY)) {
            ps.setLong(1, entity.getCompositionId());
            ps.setLong(2, entity.getAuthorsId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<CompositionHasAuthorsEntity> getAllCompositionHasAuthors() throws Exception {
        List<CompositionHasAuthorsEntity> compositionHasAuthors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_COMPOSITION_HAS_AUTHORS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long compositionId = rs.getLong("composition_id");
                    Long authorsId = rs.getLong("authors_id");
                    compositionHasAuthors.add(new CompositionHasAuthorsEntity(compositionId, authorsId));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool.close();
            }
            return compositionHasAuthors;
        }
    }

    @Override
    public List<CompositionHasAuthorsEntity> getAllCompositionHasAuthorsBy(Predicate<CompositionHasAuthorsEntity> predicate) throws Exception {
        List<CompositionHasAuthorsEntity> compositionHasAuthorsList = getAllCompositionHasAuthors();
        compositionHasAuthorsList = compositionHasAuthorsList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return compositionHasAuthorsList;
    }

    @Override
    public void getEntityById(Long compositionId) throws Exception {
        CompositionHasAuthorsEntity compositionHasAuthors = new CompositionHasAuthorsEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_COMPOSITION_HAS_AUTHORS_QUERY)) {
            ps.setLong(1, compositionId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    compositionHasAuthors.setCompositionId(rs.getLong(1));
                    System.out.println(compositionHasAuthors.getCompositionId() + "," + compositionHasAuthors.getAuthorsId());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }

    @Override
    public List<CompositionHasAuthorsEntity> updateEntity(CompositionHasAuthorsEntity entity) throws Exception {
        List<CompositionHasAuthorsEntity> updatedCompositionHasAuthors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_COMPOSITION_HAS_AUTHORS_QUERY)) {
            ps.setLong(1, entity.getAuthorsId());
            ps.setLong(3, entity.getCompositionId());
            ps.executeUpdate();
            updatedCompositionHasAuthors = getAllCompositionHasAuthors();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedCompositionHasAuthors;
    }

    @Override
    public void deleteEntity(Long compositionId) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_COMPOSITION_HAS_AUTHORS_QUERY)) {
            ps.setLong(1, compositionId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }
}
