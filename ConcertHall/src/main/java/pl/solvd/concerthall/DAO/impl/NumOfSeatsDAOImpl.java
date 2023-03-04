package pl.solvd.concerthall.DAO.impl;

import pl.solvd.concerthall.DAO.interfacesDAO.INumOfSeatsDAO;
import pl.solvd.concerthall.binary.NumOfSeats;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NumOfSeatsDAOImpl implements INumOfSeatsDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();
    private static final String GET_ALL_NUM_OF_SEATS_QUERY = "SELECT * FROM num_of_seats";
    private static final String GET_NUM_OF_SEATS_QUERY = "SELECT * FROM num_of_seats WHERE id = ?";
    private static final String INSERT_NUM_OF_SEATS_QUERY = "INSERT INTO num_of_seats (concerthalls_id, price_level_id, amount_of_seats) VALUES(?, ?)";
    private static final String UPDATE_NUM_OF_SEATS_QUERY = "UPDATE num_of_seats SET concerthalls_id = ?, price_level_id = ?, amount_of_seats = ? WHERE id = ?";
    private static final String DELETE_NUM_OF_SEATS_QUERY = "DELETE FROM num_of_seats WHERE id = ?";

    @Override
    public NumOfSeats addEntity(NumOfSeats entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_NUM_OF_SEATS_QUERY)) {
            ps.setLong(1, entity.getConcertHallsId());
            ps.setLong(2, entity.getPriceLevelId());
            ps.setInt(3, entity.getAmountOfSeats());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<NumOfSeats> getAll() {
        List<NumOfSeats> numOfSeats = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_NUM_OF_SEATS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    Long concertHallsId = rs.getLong("concerthalls_id");
                    Long priceLevelId = rs.getLong("price_level_id");
                    int amountOfSeats = rs.getInt("amount_of_seats");
                    numOfSeats.add(new NumOfSeats(id, concertHallsId, priceLevelId, amountOfSeats));
                }
            } finally {
                ConnectionPool.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return numOfSeats;
    }

    @Override
    public List<NumOfSeats> getAllNumOfSeatsBy(Predicate<NumOfSeats> predicate) {
        List<NumOfSeats> numOfSeatsList = getAll();
        numOfSeatsList = numOfSeatsList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return numOfSeatsList;
    }

    @Override
    public NumOfSeats getEntityById(Long id) {
        NumOfSeats numOfSeats = new NumOfSeats();
        try (PreparedStatement ps = connection.prepareStatement(GET_NUM_OF_SEATS_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    numOfSeats.setId(rs.getLong(1));
                    numOfSeats.setConcertHallsId(rs.getLong(2));
                    numOfSeats.setPriceLevelId(rs.getLong(3));
                    numOfSeats.setAmountOfSeats(rs.getInt(4));
                    System.out.println(NumOfSeats.getId() + "," + numOfSeats.getConcertHallsId() + "," + numOfSeats.getPriceLevelId() + "," + numOfSeats.getAmountOfSeats());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return numOfSeats;
    }

    @Override
    public List<NumOfSeats> updateEntity(NumOfSeats entity) {
        List<NumOfSeats> updatedNumOfSeats = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_NUM_OF_SEATS_QUERY)) {
            ps.setLong(1, entity.getConcertHallsId());
            ps.setLong(2, entity.getPriceLevelId());
            ps.setInt(3, entity.getAmountOfSeats());
            ps.setLong(4, NumOfSeats.getId());
            ps.executeUpdate();
            updatedNumOfSeats = getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedNumOfSeats;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_NUM_OF_SEATS_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }
}
