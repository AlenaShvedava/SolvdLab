package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.ICompositionDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Composition;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CompositionDAOImpl extends MySqlDAO implements ICompositionDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();

    private static final String GET_ALL_COMPOSITION_QUERY = "SELECT * FROM composition";
    private static final String GET_COMPOSITION_QUERY = "SELECT * FROM composition WHERE id = ?";
    private static final String INSERT_COMPOSITION_QUERY = "INSERT INTO composition (title) VALUES(?)";
    private static final String UPDATE_COMPOSITION_QUERY = "UPDATE composition SET title = ? WHERE id = ?";
    private static final String DELETE_COMPOSITION_QUERY = "DELETE FROM composition WHERE id = ?";
    private static final String GET_COMPOSITION_BY_AUTHORS_QUERY = "SELECT composition.title FROM composition JOIN composition_has_authors ON composition_id = composition.id where authors_id = ?";

    @Override
    public Composition addEntity(Composition entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_COMPOSITION_QUERY)) {
            ps.setString(1, entity.getTitle());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<Composition> getAll() throws Exception {
        List<Composition> compositions = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_COMPOSITION_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String title = rs.getString("title");
                    compositions.add(new Composition(id, title));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool.close();
            }
            return compositions;
        }
    }

    @Override
    public List<Composition> getAllCompositionBy(Predicate<Composition> predicate) throws Exception {
        List<Composition> compositionsList = getAll();
        compositionsList = compositionsList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return compositionsList;
    }

    @Override
    public List<Composition> findCompositionByAuthorsId(Long authorsId) throws SQLException {
        List<Composition> compositionByAuthor = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_COMPOSITION_BY_AUTHORS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String compositionTitle = rs.getString("title");
                    compositionByAuthor.add(new Composition(Composition.getId(), compositionTitle));
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
            return compositionByAuthor;
        }
    }

    @Override
    public List<Composition> findCompositionByProgramId(Long programId) throws SQLException {
        List<Composition> compositionByProgram = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_COMPOSITION_BY_AUTHORS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String compositionTitle = rs.getString("title");
                    compositionByProgram.add(new Composition(Composition.getId(), compositionTitle));
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
            return compositionByProgram;
        }
    }

    @Override
    public Composition getEntityById(Long id) throws Exception {
        Composition composition = new Composition();
        try (PreparedStatement ps = connection.prepareStatement(GET_COMPOSITION_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    composition.setId(rs.getLong(1));
                    composition.setTitle(rs.getString(2));
                    System.out.println(Composition.getId() + "," + composition.getTitle());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return composition;
    }

    @Override
    public List<Composition> updateEntity(Composition entity) throws Exception {
        List<Composition> updatedComposition = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_COMPOSITION_QUERY)) {
            ps.setString(1, entity.getTitle());
            ps.setLong(2, Composition.getId());
            ps.executeUpdate();
            updatedComposition = getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedComposition;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_COMPOSITION_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }
}
