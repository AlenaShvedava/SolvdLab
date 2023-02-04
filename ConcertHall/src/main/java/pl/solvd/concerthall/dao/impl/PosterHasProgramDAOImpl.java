package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IPosterHasProgramDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsHasAuthorTypesEntity;
import pl.solvd.concerthall.entities.PosterHasProgramEntity;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PosterHasProgramDAOImpl extends MySqlDAO implements IPosterHasProgramDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static Connection connection = instance.getConnection();
    private static final String GET_ALL_POSTER_HAS_PROGRAM_QUERY = "SELECT * FROM poster_has_program";
    private static final String GET_POSTER_HAS_PROGRAM_QUERY = "SELECT * FROM poster_has_program WHERE authors_id = ?";
    private static final String INSERT_POSTER_HAS_PROGRAM_QUERY = "INSERT INTO poster_has_program (poster_id, program_id) VALUES(?, ?)";
    private static final String UPDATE_POSTER_HAS_PROGRAM_QUERY = "UPDATE poster_has_program SET program_id = ? WHERE poster_id = ?";
    private static final String DELETE_POSTER_HAS_PROGRAM_QUERY = "DELETE FROM poster_has_program WHERE poster_id = ?";

    @Override
    public PosterHasProgramEntity saveEntity(PosterHasProgramEntity entity) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_POSTER_HAS_PROGRAM_QUERY)) {
            ps.setLong(1, entity.getPosterId());
            ps.setLong(2, entity.getProgramId());
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
    public void getEntityById(Long posterId) throws Exception {
        PosterHasProgramEntity posterHasProgram = new PosterHasProgramEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_POSTER_HAS_PROGRAM_QUERY)) {
            ps.setLong(1, posterId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    posterHasProgram.setPosterId(rs.getLong(1));
                    posterHasProgram.setProgramId(rs.getLong(2));
                    System.out.println(posterHasProgram.getPosterId() + "," + posterHasProgram.getProgramId());
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
    public List<PosterHasProgramEntity> updateEntity(PosterHasProgramEntity entity) throws Exception {
        List<PosterHasProgramEntity> updatedPosterHasProgram = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_POSTER_HAS_PROGRAM_QUERY)) {
            ps.setLong(1, entity.getProgramId());
            ps.setLong(2, entity.getPosterId());
            ps.executeUpdate();
            updatedPosterHasProgram= getAllPosterHasProgram();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return updatedPosterHasProgram;
    }

    @Override
    public void deleteEntity(Long posterId) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_POSTER_HAS_PROGRAM_QUERY)) {
            ps.setLong(1, posterId);
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
    public List<PosterHasProgramEntity> getAllPosterHasProgram() throws Exception {
        List<PosterHasProgramEntity> posterHasProgram = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_POSTER_HAS_PROGRAM_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long posterId = rs.getLong("poster_id");
                    Long programId = rs.getLong("program_id");
                    posterHasProgram.add(new PosterHasProgramEntity(posterId, programId));
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
            return getAllPosterHasProgram();
        }
    }

    @Override
    public List<PosterHasProgramEntity> getAllPosterHasProgramBy (Predicate<PosterHasProgramEntity> predicate) throws Exception {
        List<PosterHasProgramEntity> posterHasProgramList = getAllPosterHasProgram();
        posterHasProgramList = posterHasProgramList.stream().filter(predicate).collect(Collectors.toList());
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return posterHasProgramList;
    }
}
