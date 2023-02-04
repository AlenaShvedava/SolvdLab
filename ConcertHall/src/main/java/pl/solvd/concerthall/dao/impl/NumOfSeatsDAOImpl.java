package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.INumOfSeatsDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsEntity;
import pl.solvd.concerthall.entities.NumOfSeatsEntity;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NumOfSeatsDAOImpl extends MySqlDAO implements INumOfSeatsDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static Connection connection = instance.getConnection();

    private static final String GET_ALL_NUM_OF_SEATS_QUERY = "SELECT * FROM num_of_seats";
    private static final String GET_NUM_OF_SEATS_QUERY = "SELECT * FROM num_of_seats WHERE id = ?";
    private static final String INSERT_NUM_OF_SEATS_QUERY = "INSERT INTO num_of_seats (concerthalls_id, price_level_id, amount_of_seats) VALUES(?, ?)";
    private static final String UPDATE_NUM_OF_SEATS_QUERY = "UPDATE num_of_seats SET concerthalls_id = ?, price_level_id = ?, amount_of_seats = ? WHERE id = ?";
    private static final String DELETE_NUM_OF_SEATS_QUERY = "DELETE FROM num_of_seats WHERE id = ?";

    @Override
    public NumOfSeatsEntity saveEntity(NumOfSeatsEntity entity) {
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
    public List<NumOfSeatsEntity> getAllNumOfSeats() throws Exception {
        List<NumOfSeatsEntity> numOfSeats = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_NUM_OF_SEATS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    Long concertHallsId = rs.getLong("concerthalls_id");
                    Long priceLevelId = rs.getLong("price_level_id");
                    int amountOfSeats = rs.getInt("amount_of_seats");
                    numOfSeats.add(new NumOfSeatsEntity(id, concertHallsId, priceLevelId, amountOfSeats));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool.close();
            }
            return numOfSeats;
        }
    }

    @Override
    public List<NumOfSeatsEntity> getAllNumOfSeatsBy(Predicate<NumOfSeatsEntity> predicate) throws Exception {
        List<NumOfSeatsEntity> numOfSeatsList = getAllNumOfSeats();
        numOfSeatsList = numOfSeatsList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return numOfSeatsList;
    }

    @Override
    public void getEntityById(Long id) throws Exception {
        NumOfSeatsEntity numOfSeats = new NumOfSeatsEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_NUM_OF_SEATS_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    numOfSeats.setId(rs.getLong(1));
                    numOfSeats.setConcertHallsId(rs.getLong(2));
                    numOfSeats.setPriceLevelId(rs.getLong(3));
                    numOfSeats.setAmountOfSeats(rs.getInt(4));
                    System.out.println(numOfSeats.getId() + "," + numOfSeats.getConcertHallsId() + "," + numOfSeats.getPriceLevelId() + "," + numOfSeats.getAmountOfSeats());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }

    @Override
    public List<NumOfSeatsEntity> updateEntity(NumOfSeatsEntity entity) throws Exception {
        List<NumOfSeatsEntity> updatedNumOfSeats = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_NUM_OF_SEATS_QUERY)) {
            ps.setLong(1, entity.getConcertHallsId());
            ps.setLong(2, entity.getPriceLevelId());
            ps.setInt(3, entity.getAmountOfSeats());
            ps.setLong(4, entity.getId());
            ps.executeUpdate();
            updatedNumOfSeats = getAllNumOfSeats();
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
