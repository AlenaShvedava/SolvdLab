package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IProgramHasConcertHallsDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsHasAuthorTypesEntity;
import pl.solvd.concerthall.entities.ProgramHasConcertHallsEntity;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgramHasConcertHallsDAOImpl extends MySqlDAO implements IProgramHasConcertHallsDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static Connection connection = instance.getConnection();
    private static final String GET_ALL_PROGRAM_HAS_CONCERT_HALLS_QUERY = "SELECT * FROM program_has_concert_halls";
    private static final String GET_PROGRAM_HAS_CONCERT_HALLS_QUERY = "SELECT * FROM program_has_concert_halls WHERE program_id = ?";
    private static final String INSERT_PROGRAM_HAS_CONCERT_HALLS_QUERY = "INSERT INTO program_has_concert_halls (program_id, concert_halls_id) VALUES(?, ?)";
    private static final String UPDATE_PROGRAM_HAS_CONCERT_HALLS_QUERY = "UPDATE program_has_concert_halls SET concert_halls_id = ? WHERE program_id = ?";
    private static final String DELETE_PROGRAM_HAS_CONCERT_HALLS_QUERY = "DELETE FROM program_has_concert_halls WHERE program_id = ?";

    @Override
    public ProgramHasConcertHallsEntity saveEntity(ProgramHasConcertHallsEntity entity) throws Exception {
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
    public void getEntityById(Long programId) throws Exception {
        ProgramHasConcertHallsEntity programHasConcertHalls = new ProgramHasConcertHallsEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_PROGRAM_HAS_CONCERT_HALLS_QUERY)) {
            ps.setLong(1, programId);
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
    public List<ProgramHasConcertHallsEntity> updateEntity(ProgramHasConcertHallsEntity entity) throws Exception {
        List<ProgramHasConcertHallsEntity> updatedProgramHasConcertHalls = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PROGRAM_HAS_CONCERT_HALLS_QUERY)) {
            ps.setLong(1, entity.getConcertHallsId());
            ps.setLong(2, entity.getProgramId());
            ps.executeUpdate();
            updatedProgramHasConcertHalls= getAllProgramHasConcertHalls();
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
    public void deleteEntity(Long programId) throws Exception {
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
    public List<ProgramHasConcertHallsEntity> getAllProgramHasConcertHalls() throws Exception {
        List<ProgramHasConcertHallsEntity> programHasConcertHalls= new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_PROGRAM_HAS_CONCERT_HALLS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long programId = rs.getLong("program_id");
                    Long concertHallsId = rs.getLong("concert_halls_id");
                    programHasConcertHalls.add(new ProgramHasConcertHallsEntity(programId, concertHallsId));
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
            return getAllProgramHasConcertHalls();
        }
    }

    @Override
    public List<ProgramHasConcertHallsEntity> getAllProgramHasConcertHallsBy (Predicate<ProgramHasConcertHallsEntity> predicate) throws Exception {
        List<ProgramHasConcertHallsEntity> programHasConcertHallsList = getAllProgramHasConcertHalls();
        programHasConcertHallsList = programHasConcertHallsList.stream().filter(predicate).collect(Collectors.toList());
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return programHasConcertHallsList;
    }
}
