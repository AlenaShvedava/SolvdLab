package pl.solvd.concerthall.DAO.impl;

import pl.solvd.concerthall.DAO.interfacesDAO.IPriceLevelDAO;
import pl.solvd.concerthall.binary.PriceLevel;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PriceLevelDAOImpl implements IPriceLevelDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();

    private static final String GET_ALL_PRICE_LEVEL_QUERY = "SELECT * FROM price_level";
    private static final String GET_PRICE_LEVEL_QUERY = "SELECT * FROM price_level WHERE id = ?";
    private static final String INSERT_PRICE_LEVEL_QUERY = "INSERT INTO price_level (type, coefficient) VALUES(?, ?)";
    private static final String UPDATE_PRICE_LEVEL_QUERY = "UPDATE price_level SET type = ?, coefficient = ? WHERE id = ?";
    private static final String DELETE_PRICE_LEVEL_QUERY = "DELETE FROM price_level WHERE id = ?";

    @Override
    public PriceLevel addEntity(PriceLevel entity) {
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
    public List<PriceLevel> getAll() {
        List<PriceLevel> priceLevel = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_PRICE_LEVEL_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String type = rs.getString("type");
                    double coefficient = rs.getDouble("coefficient");
                    priceLevel.add(new PriceLevel(id, type, coefficient));
                }
            } finally {
                ConnectionPool.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return priceLevel;
    }

    @Override
    public List<PriceLevel> getAllPriceLevelBy(Predicate<PriceLevel> predicate) {
        List<PriceLevel> priceLevelList = getAll();
        priceLevelList = priceLevelList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return priceLevelList;
    }

    @Override
    public PriceLevel getEntityById(Long id) {
        PriceLevel priceLevel = new PriceLevel();
        try (PreparedStatement ps = connection.prepareStatement(GET_PRICE_LEVEL_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    priceLevel.setId(rs.getLong(1));
                    priceLevel.setType(rs.getString(2));
                    priceLevel.setCoefficient(rs.getDouble(3));
                    System.out.println(PriceLevel.getId() + "," + priceLevel.getType() + "," + priceLevel.getCoefficient());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return priceLevel;
    }

    @Override
    public List<PriceLevel> updateEntity(PriceLevel entity) {
        List<PriceLevel> updatedPriceLevel = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PRICE_LEVEL_QUERY)) {
            ps.setString(1, entity.getType());
            ps.setDouble(2, entity.getCoefficient());
            ps.setLong(3, PriceLevel.getId());
            ps.executeUpdate();
            updatedPriceLevel = getAll();
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
