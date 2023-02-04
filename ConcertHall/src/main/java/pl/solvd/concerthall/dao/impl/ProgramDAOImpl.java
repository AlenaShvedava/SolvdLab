package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IProgramDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsEntity;
import pl.solvd.concerthall.entities.ProgramEntity;
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
    private static Connection connection = instance.getConnection();

    private static final String GET_ALL_PROGRAM_QUERY = "SELECT * FROM program";
    private static final String GET_PROGRAM_QUERY = "SELECT * FROM program WHERE id = ?";
    private static final String INSERT_PROGRAM_QUERY = "INSERT INTO program (title, description, organization_id, age_limit, base_price) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE_PROGRAM_QUERY = "UPDATE program SET title = ?, description = ?, organization_id = ?, age_limit = ?, base_price = ? WHERE id = ?";
    private static final String DELETE_PROGRAM_QUERY = "DELETE FROM program WHERE id = ?";

    @Override
    public ProgramEntity saveEntity(ProgramEntity entity) {
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
    public List<ProgramEntity> getAllProgram() throws Exception {
        List<ProgramEntity> program = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_PROGRAM_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    Long organizationId = rs.getLong("organization_id");
                    String ageLimit = rs.getString("age_limit");
                    double basePrice = rs.getDouble("base_price");
                    program.add(new ProgramEntity(id, title, description, organizationId, ageLimit, basePrice));
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
    public List<ProgramEntity> getAllProgramBy(Predicate<ProgramEntity> predicate) throws Exception {
        List<ProgramEntity> programList = getAllProgram();
        programList = programList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return programList;
    }

    @Override
    public void getEntityById(Long id) throws Exception {
        ProgramEntity program = new ProgramEntity();
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
                    System.out.println(program.getId() + "," + program.getTitle() + "," + program.getDescription() + program.getOrganizationId() + "," + program.getAgeLimit() + "," + program.getBasePrice());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }

    @Override
    public List<ProgramEntity> updateEntity(ProgramEntity entity) throws Exception {
        List<ProgramEntity> updatedProgram = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PROGRAM_QUERY)) {
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getDescription());
            ps.setLong(3, entity.getOrganizationId());
            ps.setString(4, entity.getAgeLimit());
            ps.setDouble(5, entity.getBasePrice());
            ps.setLong(6, entity.getId());
            ps.executeUpdate();
            updatedProgram = getAllProgram();
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
