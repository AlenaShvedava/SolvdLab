package pl.solvd.concerthall.DAO.impl;

import pl.solvd.concerthall.DAO.interfacesDAO.IConcertHallsDAO;
import pl.solvd.concerthall.binary.ConcertHalls;
import pl.solvd.concerthall.binary.Program;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConcertHallsDAOImpl implements IConcertHallsDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();
    private static final String GET_ALL_CONCERT_HALLS_QUERY = "SELECT * FROM concert_halls";
    private static final String GET_CONCERT_HALLS_QUERY = "SELECT * FROM concert_halls WHERE id = ?";
    private static final String INSERT_CONCERT_HALLS_QUERY = "INSERT INTO concert_halls(name, address, sumNumberOfSeats, phone) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_CONCERT_HALLS_QUERY = "UPDATE concert_halls SET name = ?, address = ?, sumNumberOfSeats = ?, phone = ? WHERE id = ?";
    private static final String DELETE_CONCERT_HALLS_QUERY = "DELETE FROM concert_halls WHERE id = ?";
    private static final String GET_PROGRAMS_BY_CONCERT_HALL_ID_QUERY = "SELECT program.title, program.description, program.age_limit, program.base_price FROM concert_halls c JOIN program_has_concert_halls ON concert_halls.id = program_has_concert_halls.concert_halls_id JOIN program ON program_has_concert_halls.program_id = program.id where concert_halls.id = ?";

    @Override
    public ConcertHalls addEntity(ConcertHalls entity) {
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
    public List<ConcertHalls> getAll() {
        List<ConcertHalls> concertHalls = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_CONCERT_HALLS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    int sumNumberOfSeats = rs.getInt("sumNumber_of_seats");
                    String phone = rs.getString("phone");
                    concertHalls.add(new ConcertHalls(id, name, address, phone, sumNumberOfSeats));
                }
            } finally {
                ConnectionPool.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return concertHalls;
    }

    @Override
    public List<ConcertHalls> getAllConcertHallsBy(Predicate<ConcertHalls> predicate) throws Exception {
        List<ConcertHalls> concertHallsList = getAll();
        concertHallsList = concertHallsList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return concertHallsList;
    }

    @Override
    public List<Program> findProgramsByConcertHallId(Long concertHallsId) {
        List<Program> programByConcertHall = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_PROGRAMS_BY_CONCERT_HALL_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String programTitle = rs.getString("title");
                    String programDescription = rs.getString("description");
                    Long programOrganizationId = rs.getLong("organization_id");
                    String programAgeLimit = rs.getString("age_limit");
                    double programBasePrice = rs.getDouble("base_price");
                    programByConcertHall.add(new Program(Program.getId(), programTitle, programDescription, programOrganizationId, programAgeLimit, programBasePrice));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return programByConcertHall;
    }


    @Override
    public ConcertHalls getEntityById(Long id) {
        ConcertHalls concertHalls = new ConcertHalls();
        try (PreparedStatement ps = connection.prepareStatement(GET_CONCERT_HALLS_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    concertHalls.setId(rs.getLong(1));
                    concertHalls.setName(rs.getString(2));
                    concertHalls.setAddress(rs.getString(3));
                    concertHalls.setSumNumberOfSeats(rs.getInt(4));
                    concertHalls.setPhone(rs.getString(5));
                    System.out.println(ConcertHalls.getId() + "," + concertHalls.getName() + "," + concertHalls.getAddress() + "," + concertHalls.getSumNumberOfSeats() + "," + concertHalls.getPhone());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return concertHalls;
    }

    @Override
    public List<ConcertHalls> updateEntity(ConcertHalls entity) {
        List<ConcertHalls> updatedConcertHalls = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_CONCERT_HALLS_QUERY)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getAddress());
            ps.setInt(2, entity.getSumNumberOfSeats());
            ps.setString(2, entity.getPhone());
            ps.setLong(3, ConcertHalls.getId());
            ps.executeUpdate();
            updatedConcertHalls = getAll();
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
