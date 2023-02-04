package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.ICompositionDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.CompositionEntity;
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
    private static Connection connection = instance.getConnection();

    private static final String GET_ALL_COMPOSITION_QUERY = "SELECT * FROM composition";
    private static final String GET_COMPOSITION_QUERY = "SELECT * FROM composition WHERE id = ?";
    private static final String INSERT_COMPOSITION_QUERY = "INSERT INTO composition (title) VALUES(?)";
    private static final String UPDATE_COMPOSITION_QUERY = "UPDATE composition SET title = ? WHERE id = ?";
    private static final String DELETE_COMPOSITION_QUERY = "DELETE FROM composition WHERE id = ?";

    @Override
    public CompositionEntity saveEntity(CompositionEntity entity) {
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
    public List<CompositionEntity> getAllComposition() throws Exception {
        List<CompositionEntity> compositions = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_COMPOSITION_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String title = rs.getString("title");
                    compositions.add(new CompositionEntity((long) id, title));
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
    public List<CompositionEntity> getAllCompositionBy(Predicate<CompositionEntity> predicate) throws Exception {
        List<CompositionEntity> compositionsList = getAllComposition();
        compositionsList = compositionsList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return compositionsList;
    }

    @Override
    public void getEntityById(Long id) throws Exception {
        CompositionEntity composition = new CompositionEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_COMPOSITION_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    composition.setId(rs.getLong(1));
                    composition.setTitle(rs.getString(2));
                    System.out.println(composition.getId() + "," + composition.getTitle());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }

    @Override
    public List<CompositionEntity> updateEntity(CompositionEntity entity) throws Exception {
        List<CompositionEntity> updatedComposition = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_COMPOSITION_QUERY)) {
            ps.setString(1, entity.getTitle());
            ps.setLong(2, entity.getId());
            ps.executeUpdate();
            updatedComposition = getAllComposition();
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
