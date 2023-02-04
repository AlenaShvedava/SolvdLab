package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IPriceLevelDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsEntity;
import pl.solvd.concerthall.entities.PriceLevelEntity;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PriceLevelDAOImpl extends MySqlDAO implements IPriceLevelDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static Connection connection = instance.getConnection();

    private static final String GET_ALL_PRICE_LEVEL_QUERY = "SELECT * FROM price_level";
    private static final String GET_PRICE_LEVEL_QUERY = "SELECT * FROM price_level WHERE id = ?";
    private static final String INSERT_PRICE_LEVEL_QUERY = "INSERT INTO price_level (type, coefficient) VALUES(?, ?)";
    private static final String UPDATE_PRICE_LEVEL_QUERY = "UPDATE price_level SET type = ?, coefficient = ? WHERE id = ?";
    private static final String DELETE_PRICE_LEVEL_QUERY = "DELETE FROM price_level WHERE id = ?";

    @Override
    public PriceLevelEntity saveEntity(PriceLevelEntity entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_PRICE_LEVEL_QUERY)) {
            ps.setString(1, entity.getType());
            ps.setDouble(2, entity.getCoefficient());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<PriceLevelEntity> getAllPriceLevel() throws Exception {
        List<PriceLevelEntity> priceLevel = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_PRICE_LEVEL_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String type = rs.getString("type");
                    double coefficient = rs.getDouble("coefficient");
                    priceLevel.add(new PriceLevelEntity(id, type, coefficient));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool.close();
            }
            return priceLevel;
        }
    }

    @Override
    public List<PriceLevelEntity> getAllPriceLevelBy(Predicate<PriceLevelEntity> predicate) throws Exception {
        List<PriceLevelEntity> priceLevelList = getAllPriceLevel();
        priceLevelList = priceLevelList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return priceLevelList;
    }

    @Override
    public void getEntityById(Long id) throws Exception {
        PriceLevelEntity priceLevel = new PriceLevelEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_PRICE_LEVEL_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    priceLevel.setId(rs.getLong(1));
                    priceLevel.setType(rs.getString(2));
                    priceLevel.setCoefficient(rs.getDouble(3));
                    System.out.println(priceLevel.getId() + "," + priceLevel.getType() + "," + priceLevel.getCoefficient());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }

    @Override
    public List<PriceLevelEntity> updateEntity(PriceLevelEntity entity) throws Exception {
        List<PriceLevelEntity> updatedPriceLevel = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PRICE_LEVEL_QUERY)) {
            ps.setString(1, entity.getType());
            ps.setDouble(2, entity.getCoefficient());
            ps.setLong(3, entity.getId());
            ps.executeUpdate();
            updatedPriceLevel = getAllPriceLevel();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedPriceLevel;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_PRICE_LEVEL_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }
}
