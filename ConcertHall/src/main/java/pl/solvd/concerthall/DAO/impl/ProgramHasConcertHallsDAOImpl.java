package pl.solvd.concerthall.DAO.impl;

import pl.solvd.concerthall.DAO.interfacesDAO.IProgramHasConcertHallsDAO;
import pl.solvd.concerthall.binary.ProgramHasConcertHalls;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgramHasConcertHallsDAOImpl implements IProgramHasConcertHallsDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();
    private static final String GET_ALL_PROGRAM_HAS_CONCERT_HALLS_QUERY = "SELECT * FROM program_has_concert_halls";
    private static final String GET_PROGRAM_HAS_CONCERT_HALLS_QUERY = "SELECT * FROM program_has_concert_halls WHERE program_id = ?";
    private static final String INSERT_PROGRAM_HAS_CONCERT_HALLS_QUERY = "INSERT INTO program_has_concert_halls (program_id, concert_halls_id) VALUES(?, ?)";
    private static final String UPDATE_PROGRAM_HAS_CONCERT_HALLS_QUERY = "UPDATE program_has_concert_halls SET concert_halls_id = ? WHERE program_id = ?";
    private static final String DELETE_PROGRAM_HAS_CONCERT_HALLS_QUERY = "DELETE FROM program_has_concert_halls WHERE program_id = ?";

    @Override
    public ProgramHasConcertHalls addEntity(ProgramHasConcertHalls entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_PROGRAM_HAS_CONCERT_HALLS_QUERY)) {
            ps.setLong(1, entity.getProgramId());
            ps.setLong(2, entity.getConcertHallsId());
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
    public void getEntityByProgramIdAndConcertHallsId(Long programId, Long concertHallsId) throws Exception {
        ProgramHasConcertHalls programHasConcertHalls = new ProgramHasConcertHalls();
        try (PreparedStatement ps = connection.prepareStatement(GET_PROGRAM_HAS_CONCERT_HALLS_QUERY)) {
            ps.setLong(1, programId);
            ps.setLong(2, concertHallsId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    programHasConcertHalls.setProgramId(rs.getLong(1));
                    programHasConcertHalls.setConcertHallsId(rs.getLong(2));
                    System.out.println(programHasConcertHalls.getProgramId() + "," + programHasConcertHalls.getConcertHallsId());
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
    public List<ProgramHasConcertHalls> updateEntity(ProgramHasConcertHalls entity) {
        List<ProgramHasConcertHalls> updatedProgramHasConcertHalls = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PROGRAM_HAS_CONCERT_HALLS_QUERY)) {
            ps.setLong(1, entity.getConcertHallsId());
            ps.setLong(2, entity.getProgramId());
            ps.executeUpdate();
            updatedProgramHasConcertHalls = getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return updatedProgramHasConcertHalls;
    }

    @Override
    public void deleteEntity(Long programId) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_PROGRAM_HAS_CONCERT_HALLS_QUERY)) {
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
    public List<ProgramHasConcertHalls> getAll() {
        List<ProgramHasConcertHalls> programHasConcertHalls = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_PROGRAM_HAS_CONCERT_HALLS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long programId = rs.getLong("program_id");
                    Long concertHallsId = rs.getLong("concert_halls_id");
                    programHasConcertHalls.add(new ProgramHasConcertHalls(programId, concertHallsId));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return programHasConcertHalls;
    }

    @Override
    public List<ProgramHasConcertHalls> getAllProgramHasConcertHallsBy(Predicate<ProgramHasConcertHalls> predicate) throws Exception {
        List<ProgramHasConcertHalls> programHasConcertHallsList = getAll();
        programHasConcertHallsList = programHasConcertHallsList.stream().filter(predicate).collect(Collectors.toList());
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return programHasConcertHallsList;
    }
}
