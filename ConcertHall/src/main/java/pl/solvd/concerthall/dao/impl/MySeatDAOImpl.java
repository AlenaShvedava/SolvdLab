package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IMySeatDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsEntity;
import pl.solvd.concerthall.entities.MySeatEntity;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MySeatDAOImpl extends MySqlDAO implements IMySeatDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static Connection connection = instance.getConnection();

    private static final String GET_ALL_MY_SEAT_QUERY = "SELECT * FROM my_seat";
    private static final String GET_MY_SEAT_QUERY = "SELECT * FROM my_seat WHERE id = ?";
    private static final String INSERT_MY_SEAT_QUERY = "INSERT INTO my_seat (row_number, seat_number, num_of_seats_id) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_MY_SEAT_QUERY = "UPDATE my_seat SET row_number = ?, seat_number = ? num_of_seats_id = ? WHERE id = ?";
    private static final String DELETE_MY_SEAT_QUERY = "DELETE FROM my_seat WHERE id = ?";

    @Override
    public MySeatEntity saveEntity(MySeatEntity entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_MY_SEAT_QUERY)) {
            ps.setInt(1, entity.getRowNumber());
            ps.setInt(2, entity.getSeatNumber());
            ps.setInt(2, entity.getNumOfSeatsId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<MySeatEntity> getAllMySeat() throws Exception {
        List<MySeatEntity> mySeat = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_MY_SEAT_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    int rowNumber = rs.getInt("row_number");
                    int seatNumber = rs.getInt("seat_number");
                    int numOfSeatsId = rs.getInt("num_of_seats_id");
                    mySeat.add(new MySeatEntity (id, rowNumber, seatNumber, numOfSeatsId));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool.close();
            }
            return mySeat;
        }
    }

    @Override
    public List<MySeatEntity> getAllMySeatBy (Predicate<MySeatEntity> predicate) throws Exception {
        List<MySeatEntity> mySeatList = getAllMySeat();
        mySeatList = mySeatList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return mySeatList;
    }

    @Override
    public void getEntityById(Long id) throws Exception {
        MySeatEntity mySeat = new MySeatEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_MY_SEAT_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    mySeat.setId(rs.getLong(1));
                    mySeat.setRowNumber(rs.getInt(2));
                    mySeat.setSeatNumber(rs.getInt(3));
                    mySeat.setNumOfSeatsId(rs.getInt(4));
                    System.out.println(mySeat.getId() + "," + mySeat.getRowNumber() + "," + mySeat.getSeatNumber() + "," + mySeat.getNumOfSeatsId());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }

    @Override
    public List<MySeatEntity> updateEntity(MySeatEntity entity) throws Exception {
        List<MySeatEntity> updatedMySeat = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_MY_SEAT_QUERY)) {
            ps.setInt(1, entity.getRowNumber());
            ps.setInt(2, entity.getSeatNumber());
            ps.setInt(3, entity.getNumOfSeatsId());
            ps.setLong(4, entity.getId());
            ps.executeUpdate();
            updatedMySeat = getAllMySeat();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedMySeat;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_MY_SEAT_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }
}
