package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IProgramDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Program;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgramDAOImpl extends MySqlDAO implements IProgramDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();

    private static final String GET_ALL_PROGRAM_QUERY = "SELECT * FROM program";
    private static final String GET_PROGRAM_QUERY = "SELECT * FROM program WHERE id = ?";
    private static final String INSERT_PROGRAM_QUERY = "INSERT INTO program (title, description, organization_id, age_limit, base_price) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE_PROGRAM_QUERY = "UPDATE program SET title = ?, description = ?, organization_id = ?, age_limit = ?, base_price = ? WHERE id = ?";
    private static final String DELETE_PROGRAM_QUERY = "DELETE FROM program WHERE id = ?";
    private static final String GET_PROGRAM_BY_CONCERT_HALLS_ID_QUERY = "SELECT program.title, program.description, program.age_limit, program.base_price FROM program JOIN program_has_concert_halls ON program_id = program.id where concert_halls_id = ?";

    @Override
    public Program addEntity(Program entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_PROGRAM_QUERY)) {
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getDescription());
            ps.setLong(2, entity.getOrganizationId());
            ps.setString(2, entity.getAgeLimit());
            ps.setDouble(2, entity.getBasePrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<Program> getAll() throws Exception {
        List<Program> program = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_PROGRAM_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    Long organizationId = rs.getLong("organization_id");
                    String ageLimit = rs.getString("age_limit");
                    double basePrice = rs.getDouble("base_price");
                    program.add(new Program(id, title, description, organizationId, ageLimit, basePrice));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool.close();
            }
            return program;
        }
    }

    @Override
    public List<Program> getAllProgramBy(Predicate<Program> predicate) throws Exception {
        List<Program> programList = getAll();
        programList = programList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return programList;
    }

    @Override
    public List<Program> findProgramByConcertHallsId(Long concertHallsId) throws SQLException {
        List<Program> programByConcertHall = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_PROGRAM_BY_CONCERT_HALLS_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String programTitle = rs.getString("title");
                    String programDescription = rs.getString("description");
                    Long programOrganizationId = rs.getLong("organization_id");
                    String programAgeLimit = rs.getString("age_limit");
                    double programBasePrice = rs.getDouble("base_price");
                    programByConcertHall.add(new Program(Program.getId(), programTitle, programDescription, programOrganizationId, programAgeLimit, programBasePrice));
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
            return programByConcertHall;
        }
    }

    @Override
    public List<Program> findProgramByCompositionId(Long compositionId) throws SQLException {
        List<Program> programByComposition = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_PROGRAM_BY_CONCERT_HALLS_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String programTitle = rs.getString("title");
                    String programDescription = rs.getString("description");
                    Long programOrganizationId = rs.getLong("organization_id");
                    String programAgeLimit = rs.getString("age_limit");
                    double programBasePrice = rs.getDouble("base_price");
                    programByComposition.add(new Program(Program.getId(), programTitle, programDescription, programOrganizationId, programAgeLimit, programBasePrice));
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
            return programByComposition;
        }
    }

    @Override
    public List<Program> findProgramByGenreId(Long genreId) throws SQLException {
        List<Program> programByGenre = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_PROGRAM_BY_CONCERT_HALLS_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String programTitle = rs.getString("title");
                    String programDescription = rs.getString("description");
                    Long programOrganizationId = rs.getLong("organization_id");
                    String programAgeLimit = rs.getString("age_limit");
                    double programBasePrice = rs.getDouble("base_price");
                    programByGenre.add(new Program(Program.getId(), programTitle, programDescription, programOrganizationId, programAgeLimit, programBasePrice));
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
            return programByGenre;
        }
    }

    @Override
    public List <Program> findProgramByPosterId(Long posterId) throws SQLException {
        List<Program> programByPoster = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_PROGRAM_BY_CONCERT_HALLS_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String programTitle = rs.getString("title");
                    String programDescription = rs.getString("description");
                    Long programOrganizationId = rs.getLong("organization_id");
                    String programAgeLimit = rs.getString("age_limit");
                    double programBasePrice = rs.getDouble("base_price");
                    programByPoster.add(new Program(Program.getId(), programTitle, programDescription, programOrganizationId, programAgeLimit, programBasePrice));
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
            return programByPoster;
        }
    }

    @Override
    public Program getEntityById(Long id) throws Exception {
        Program program = new Program();
        try (PreparedStatement ps = connection.prepareStatement(GET_PROGRAM_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    program.setId(rs.getLong(1));
                    program.setTitle(rs.getString(2));
                    program.setDescription(rs.getString(3));
                    program.setOrganizationId(rs.getLong(4));
                    program.setAgeLimit(rs.getString(5));
                    program.setBasePrice(rs.getDouble(6));
                    System.out.println(Program.getId() + "," + program.getTitle() + "," + program.getDescription() + program.getOrganizationId() + "," + program.getAgeLimit() + "," + program.getBasePrice());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return program;
    }

    @Override
    public List<Program> updateEntity(Program entity) throws Exception {
        List<Program> updatedProgram = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PROGRAM_QUERY)) {
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getDescription());
            ps.setLong(3, entity.getOrganizationId());
            ps.setString(4, entity.getAgeLimit());
            ps.setDouble(5, entity.getBasePrice());
            ps.setLong(6, Program.getId());
            ps.executeUpdate();
            updatedProgram = getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedProgram;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_PROGRAM_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }
}
