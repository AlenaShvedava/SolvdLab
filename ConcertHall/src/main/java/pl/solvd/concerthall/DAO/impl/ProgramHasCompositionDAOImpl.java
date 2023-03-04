package pl.solvd.concerthall.DAO.impl;

import pl.solvd.concerthall.DAO.interfacesDAO.IProgramHasCompositionDAO;
import pl.solvd.concerthall.binary.ProgramHasComposition;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgramHasCompositionDAOImpl implements IProgramHasCompositionDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();
    private static final String GET_ALL_PROGRAM_HAS_COMPOSITION_QUERY = "SELECT * FROM program_has_composition";
    private static final String GET_PROGRAM_HAS_COMPOSITION_QUERY = "SELECT * FROM program_has_composition WHERE program_id = ?";
    private static final String INSERT_PROGRAM_HAS_COMPOSITION_QUERY = "INSERT INTO program_has_composition (program_id, composition_id) VALUES(?, ?)";
    private static final String UPDATE_PROGRAM_HAS_COMPOSITION_QUERY = "UPDATE program_has_composition SET composition_id = ? WHERE program_id = ?";
    private static final String DELETE_PROGRAM_HAS_COMPOSITION_QUERY = "DELETE FROM program_has_composition WHERE program_id = ?";

    @Override
    public ProgramHasComposition addEntity(ProgramHasComposition entity) {
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
    public void getEntityByProgramIdAndCompositionId(Long programId, Long compositionId) {
        ProgramHasComposition programHasComposition = new ProgramHasComposition();
        try (PreparedStatement ps = connection.prepareStatement(GET_PROGRAM_HAS_COMPOSITION_QUERY)) {
            ps.setLong(1, programId);
            ps.setLong(2, compositionId);
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
    public List<ProgramHasComposition> updateEntity(ProgramHasComposition entity) {
        List<ProgramHasComposition> updatedProgramHasComposition = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PROGRAM_HAS_COMPOSITION_QUERY)) {
            ps.setLong(1, entity.getCompositionId());
            ps.setLong(2, entity.getProgramId());
            ps.executeUpdate();
            updatedProgramHasComposition = getAll();
        } catch (Exception e) {
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
    public void deleteEntity(Long programId) {
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
    public List<ProgramHasComposition> getAll() {
        List<ProgramHasComposition> programHasComposition = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_PROGRAM_HAS_COMPOSITION_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long programId = rs.getLong("authors_id");
                    Long compositionId = rs.getLong("author_type_id");
                    programHasComposition.add(new ProgramHasComposition(programId, compositionId));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return programHasComposition;
    }

    @Override
    public List<ProgramHasComposition> getAllProgramHasCompositionBy(Predicate<ProgramHasComposition> predicate) throws Exception {
        List<ProgramHasComposition> programHasCompositionList = getAll();
        programHasCompositionList = programHasCompositionList.stream().filter(predicate).collect(Collectors.toList());
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return programHasCompositionList;
    }
}
