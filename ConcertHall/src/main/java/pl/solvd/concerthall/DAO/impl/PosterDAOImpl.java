package pl.solvd.concerthall.DAO.impl;

import pl.solvd.concerthall.DAO.interfacesDAO.IPosterDAO;
import pl.solvd.concerthall.binary.Poster;
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

public class PosterDAOImpl implements IPosterDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();
    private static final String GET_ALL_POSTER_QUERY = "SELECT * FROM poster";
    private static final String GET_POSTER_QUERY = "SELECT * FROM poster WHERE id = ?";
    private static final String INSERT_POSTER_QUERY = "INSERT INTO poster (year, month, day, time) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_POSTER_QUERY = "UPDATE poster SET year = ?, month = ?, day = ?, time = ? WHERE id = ?";
    private static final String DELETE_POSTER_QUERY = "DELETE FROM poster WHERE id = ?";
    private static final String GET_PROGRAMS_BY_POSTER_ID_QUERY = "SELECT program.title, program.description, program.age_limit, program.base_price FROM poster JOIN poster_has_program ON poster.id = poster_has_program.poster_id JOIN program ON poster_has_program.program_id = program.id where poster.id = ?";

    @Override
    public Poster addEntity(Poster entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_POSTER_QUERY)) {
            ps.setInt(1, entity.getYear());
            ps.setInt(2, entity.getMonth());
            ps.setInt(2, entity.getDay());
            ps.setDouble(2, entity.getTime());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<Poster> getAll() {
        List<Poster> poster = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_POSTER_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    int year = rs.getInt("year");
                    int month = rs.getInt("month");
                    int day = rs.getInt("day");
                    double time = rs.getDouble("time");
                    poster.add(new Poster(id, year, month, day, time));
                }
            } finally {
                ConnectionPool.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return poster;
    }

    @Override
    public List<Poster> getAllPosterBy(Predicate<Poster> predicate) {
        List<Poster> posterList = getAll();
        posterList = posterList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return posterList;
    }

    @Override
    public Poster getEntityById(Long id) {
        Poster poster = new Poster();
        try (PreparedStatement ps = connection.prepareStatement(GET_POSTER_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    poster.setId(rs.getLong(1));
                    poster.setYear(rs.getInt(2));
                    poster.setMonth(rs.getInt(3));
                    poster.setDay(rs.getInt(4));
                    poster.setTime(rs.getDouble(5));
                    System.out.println(Poster.getId() + "," + poster.getYear() + "," + poster.getMonth() + poster.getDay() + "," + poster.getTime());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return poster;
    }

    @Override
    public List<Program> findProgramsByPosterId(Long posterId) {
        List<Program> programByPoster = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_PROGRAMS_BY_POSTER_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String programTitle = rs.getString("title");
                    String programDescription = rs.getString("description");
                    Long programOrganizationId = rs.getLong("organization_id");
                    String programAgeLimit = rs.getString("age_limit");
                    double programBasePrice = rs.getDouble("base_price");
                    programByPoster.add(new Program(Program.getId(), programTitle, programDescription, programOrganizationId, programAgeLimit, programBasePrice));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return programByPoster;
    }

    @Override
    public List<Poster> updateEntity(Poster entity) {
        List<Poster> updatedPoster = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_POSTER_QUERY)) {
            ps.setInt(1, entity.getYear());
            ps.setInt(2, entity.getMonth());
            ps.setInt(3, entity.getDay());
            ps.setDouble(4, entity.getTime());
            ps.setLong(5, Poster.getId());
            ps.executeUpdate();
            updatedPoster = getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedPoster;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_POSTER_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }
}
