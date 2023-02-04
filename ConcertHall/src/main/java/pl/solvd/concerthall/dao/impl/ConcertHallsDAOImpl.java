package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IConcertHallsDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsEntity;
import pl.solvd.concerthall.entities.ConcertHallsEntity;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConcertHallsDAOImpl extends MySqlDAO implements IConcertHallsDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static Connection connection = instance.getConnection();

    private static final String GET_ALL_CONCERT_HALLS_QUERY = "SELECT * FROM concerthalls";
    private static final String GET_CONCERT_HALLS_QUERY = "SELECT * FROM concerthalls WHERE id = ?";
    private static final String INSERT_CONCERT_HALLS_QUERY = "INSERT INTO concerthalls(name, address, sumNumberOfSeats, phone) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_CONCERT_HALLS_QUERY = "UPDATE concerthalls SET name = ?, address = ?, sumNumberOfSeats = ?, phone = ? WHERE id = ?";
    private static final String DELETE_CONCERT_HALLS_QUERY = "DELETE FROM concerthalls WHERE id = ?";

    @Override
    public ConcertHallsEntity saveEntity(ConcertHallsEntity entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_CONCERT_HALLS_QUERY)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getAddress());
            ps.setInt(3, entity.getSumNumberOfSeats());
            ps.setString(4, entity.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<ConcertHallsEntity> getAllConcertHalls() throws Exception {
        List<ConcertHallsEntity> concertHalls = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_CONCERT_HALLS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    int sumNumberOfSeats = rs.getInt("sumNumber_of_seats");
                    String phone = rs.getString("phone");
                    concertHalls.add(new ConcertHallsEntity(id, name, address, sumNumberOfSeats,phone));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool.close();
            }
            return concertHalls;
        }
    }

    @Override
    public List<ConcertHallsEntity> getAllConcertHallsBy(Predicate<ConcertHallsEntity> predicate) throws Exception {
        List<ConcertHallsEntity> concertHallsList = getAllConcertHalls();
        concertHallsList = concertHallsList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return concertHallsList;
    }

    @Override
    public void getEntityById(Long id) throws Exception {
        ConcertHallsEntity concertHalls = new ConcertHallsEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_CONCERT_HALLS_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    concertHalls.setId(rs.getLong(1));
                    concertHalls.setName(rs.getString(2));
                    concertHalls.setAddress(rs.getString(3));
                    concertHalls.setSumNumberOfSeats(rs.getInt(4));
                    concertHalls.setPhone(rs.getString(5));
                    System.out.println(concertHalls.getId() + "," + concertHalls.getName() + "," + concertHalls.getAddress() + "," + concertHalls.getSumNumberOfSeats() + "," + concertHalls.getPhone());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }

    @Override
    public List<ConcertHallsEntity> updateEntity(ConcertHallsEntity entity) throws Exception {
        List<ConcertHallsEntity> updatedConcertHalls = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_CONCERT_HALLS_QUERY)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getAddress());
            ps.setInt(2, entity.getSumNumberOfSeats());
            ps.setString(2, entity.getPhone());
            ps.setLong(3, entity.getId());
            ps.executeUpdate();
            updatedConcertHalls = getAllConcertHalls();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedConcertHalls;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_CONCERT_HALLS_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }
}
