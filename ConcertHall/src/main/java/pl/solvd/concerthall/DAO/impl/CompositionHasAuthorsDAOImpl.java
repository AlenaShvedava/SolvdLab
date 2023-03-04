package pl.solvd.concerthall.DAO.impl;

import pl.solvd.concerthall.DAO.interfacesDAO.ICompositionHasAuthorsDAO;
import pl.solvd.concerthall.binary.CompositionHasAuthors;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CompositionHasAuthorsDAOImpl implements ICompositionHasAuthorsDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();

    private static final String GET_ALL_COMPOSITION_HAS_AUTHORS_QUERY = "SELECT * FROM composition_has_authors";
    private static final String GET_COMPOSITION_HAS_AUTHORS_QUERY = "SELECT * FROM composition_has_authors WHERE composition_id = ?";
    private static final String INSERT_COMPOSITION_HAS_AUTHORS_QUERY = "INSERT INTO composition_has_authors (composition_id, authors_id) VALUES(?, ?)";
    private static final String UPDATE_COMPOSITION_HAS_AUTHORS_QUERY = "UPDATE composition_has_authors SET authors_id= ? WHERE composition_id = ?";
    private static final String DELETE_COMPOSITION_HAS_AUTHORS_QUERY = "DELETE FROM composition_has_authors WHERE composition_id = ?";

    @Override
    public CompositionHasAuthors addEntity(CompositionHasAuthors entity) {
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
    public List<CompositionHasAuthors> getAll() {
        List<CompositionHasAuthors> compositionHasAuthors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_COMPOSITION_HAS_AUTHORS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long compositionId = rs.getLong("composition_id");
                    Long authorsId = rs.getLong("authors_id");
                    compositionHasAuthors.add(new CompositionHasAuthors(compositionId, authorsId));
                }
            } finally {
                ConnectionPool.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return compositionHasAuthors;
    }


    @Override
    public List<CompositionHasAuthors> getAllCompositionHasAuthorsBy(Predicate<CompositionHasAuthors> predicate) throws Exception {
        List<CompositionHasAuthors> compositionHasAuthorsList = getAll();
        compositionHasAuthorsList = compositionHasAuthorsList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return compositionHasAuthorsList;
    }

    @Override
    public void getEntityByCompositionIdAndAuthorsId(Long compositionId, Long authorsId) {
        CompositionHasAuthors compositionHasAuthors = new CompositionHasAuthors();
        try (PreparedStatement ps = connection.prepareStatement(GET_COMPOSITION_HAS_AUTHORS_QUERY)) {
            ps.setLong(1, compositionId);
            ps.setLong(2, authorsId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    compositionHasAuthors.setCompositionId(rs.getLong(1));
                    compositionHasAuthors.setAuthorsId(rs.getLong(2));
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
    public List<CompositionHasAuthors> updateEntity(CompositionHasAuthors entity) {
        List<CompositionHasAuthors> updatedCompositionHasAuthors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_COMPOSITION_HAS_AUTHORS_QUERY)) {
            ps.setLong(1, entity.getAuthorsId());
            ps.setLong(3, entity.getCompositionId());
            ps.executeUpdate();
            updatedCompositionHasAuthors = getAll();
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
