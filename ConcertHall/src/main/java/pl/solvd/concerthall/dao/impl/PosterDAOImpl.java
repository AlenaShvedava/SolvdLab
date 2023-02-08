package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IPosterDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Poster;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PosterDAOImpl extends MySqlDAO implements IPosterDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();

    private static final String GET_ALL_POSTER_QUERY = "SELECT * FROM poster";
    private static final String GET_POSTER_QUERY = "SELECT * FROM poster WHERE id = ?";
    private static final String INSERT_POSTER_QUERY = "INSERT INTO poster (year, month, day, time) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_POSTER_QUERY = "UPDATE poster SET year = ?, month = ?, day = ?, time = ? WHERE id = ?";
    private static final String DELETE_POSTER_QUERY = "DELETE FROM poster WHERE id = ?";
    private static final String GET_POSTER_BY_PROGRAM_ID_QUERY = "SELECT poster.year, poster.month, poster.day, poster.time FROM poster JOIN poster_has_program ON poster_id = poster.id where program_id = ?";

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
    public List<Poster> getAll() throws Exception {
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
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool.close();
            }
            return poster;
        }
    }

    @Override
    public List<Poster> getAllPosterBy(Predicate<Poster> predicate) throws Exception {
        List<Poster> posterList = getAll();
        posterList = posterList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return posterList;
    }

    @Override
    public List<Poster> findPosterByProgramId(Long programId) throws SQLException {
        List<Poster> posterByProgram = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_POSTER_BY_PROGRAM_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long posterId = rs.getLong("id");
                    int posterYear = rs.getInt("year");
                    int posterMonth = rs.getInt("month");
                    int posterDay = rs.getInt("day");
                    double posterTime = rs.getDouble("time");
                    posterByProgram.add(new Poster(posterId, posterYear, posterMonth, posterDay, posterTime));
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
            return posterByProgram;
        }
    }

    @Override
    public Poster getEntityById(Long id) throws Exception {
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
    public List<Poster> updateEntity(Poster entity) throws Exception {
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
