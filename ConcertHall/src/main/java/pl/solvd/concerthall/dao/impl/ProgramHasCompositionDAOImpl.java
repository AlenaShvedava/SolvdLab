package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IProgramHasCompositionDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsHasAuthorTypesEntity;
import pl.solvd.concerthall.entities.ProgramHasCompositionEntity;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgramHasCompositionDAOImpl extends MySqlDAO implements IProgramHasCompositionDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static Connection connection = instance.getConnection();
    private static final String GET_ALL_PROGRAM_HAS_COMPOSITION_QUERY = "SELECT * FROM program_has_composition";
    private static final String GET_PROGRAM_HAS_COMPOSITION_QUERY = "SELECT * FROM program_has_composition WHERE program_id = ?";
    private static final String INSERT_PROGRAM_HAS_COMPOSITION_QUERY = "INSERT INTO program_has_composition (program_id, composition_id) VALUES(?, ?)";
    private static final String UPDATE_PROGRAM_HAS_COMPOSITION_QUERY = "UPDATE program_has_composition SET composition_id = ? WHERE program_id = ?";
    private static final String DELETE_PROGRAM_HAS_COMPOSITION_QUERY = "DELETE FROM program_has_composition WHERE program_id = ?";

    @Override
    public ProgramHasCompositionEntity saveEntity(ProgramHasCompositionEntity entity) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_PROGRAM_HAS_COMPOSITION_QUERY)) {
            ps.setLong(1, entity.getProgramId());
            ps.setLong(2, entity.getCompositionId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return entity;
    }

    @Override
    public void getEntityById(Long programId) throws Exception {
        ProgramHasCompositionEntity programHasComposition = new ProgramHasCompositionEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_PROGRAM_HAS_COMPOSITION_QUERY)) {
            ps.setLong(1, programId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    programHasComposition.setProgramId(rs.getLong(1));
                    programHasComposition.setCompositionId(rs.getLong(2));
                    System.out.println(programHasComposition.getProgramId() + "," + programHasComposition.getCompositionId());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public List<ProgramHasCompositionEntity> updateEntity(ProgramHasCompositionEntity entity) throws Exception {
        List<ProgramHasCompositionEntity> updatedProgramHasComposition = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PROGRAM_HAS_COMPOSITION_QUERY)) {
            ps.setLong(1, entity.getCompositionId());
            ps.setLong(2, entity.getProgramId());
            ps.executeUpdate();
            updatedProgramHasComposition = getAllProgramHasComposition();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return updatedProgramHasComposition;
    }

    @Override
    public void deleteEntity(Long programId) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_PROGRAM_HAS_COMPOSITION_QUERY)) {
            ps.setLong(1, programId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public List<ProgramHasCompositionEntity> getAllProgramHasComposition() throws Exception {
        List<ProgramHasCompositionEntity> programHasComposition = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_PROGRAM_HAS_COMPOSITION_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long programId = rs.getLong("authors_id");
                    Long compositionId = rs.getLong("author_type_id");
                    programHasComposition.add(new ProgramHasCompositionEntity(programId, compositionId));
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
            return getAllProgramHasComposition();
        }
    }

    @Override
    public List<ProgramHasCompositionEntity> getAllProgramHasCompositionBy (Predicate<ProgramHasCompositionEntity> predicate) throws Exception {
        List<ProgramHasCompositionEntity> programHasCompositionList = getAllProgramHasComposition();
        programHasCompositionList = programHasCompositionList.stream().filter(predicate).collect(Collectors.toList());
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return programHasCompositionList;
    }
}
