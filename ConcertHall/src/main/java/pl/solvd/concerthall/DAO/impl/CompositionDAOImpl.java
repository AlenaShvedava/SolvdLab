package pl.solvd.concerthall.DAO.impl;

import pl.solvd.concerthall.DAO.interfacesDAO.ICompositionDAO;
import pl.solvd.concerthall.binary.Authors;
import pl.solvd.concerthall.binary.Composition;
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

public class CompositionDAOImpl implements ICompositionDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();
    private static final String GET_ALL_COMPOSITION_QUERY = "SELECT * FROM composition";
    private static final String GET_COMPOSITION_QUERY = "SELECT * FROM composition WHERE id = ?";
    private static final String INSERT_COMPOSITION_QUERY = "INSERT INTO composition (title) VALUES(?)";
    private static final String UPDATE_COMPOSITION_QUERY = "UPDATE composition SET title = ? WHERE id = ?";
    private static final String DELETE_COMPOSITION_QUERY = "DELETE FROM composition WHERE id = ?";
    private static final String GET_AUTHORS_BY_COMPOSITION_ID_QUERY = " SELECT authors.first_name, authors.last_name FROM composition JOIN composition_has_authors ON composition.id = composition_has_authors.composition_id JOIN authors ON composition_has_authors.authors_id = authors.id where composition.id = ?";
    private static final String GET_PROGRAM_BY_COMPOSITION_ID_QUERY = "SELECT program.description, program.age_limit, program.base_price FROM composition JOIN program_has_composition ON composition.id = program_has_composition.composition_id JOIN program ON program_has_composition.program_id = program.id where composition.id = ?";

    @Override
    public Composition addEntity(Composition entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_COMPOSITION_QUERY)) {
            ps.setString(1, entity.getTitle());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<Composition> getAll() {
        List<Composition> compositions = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_COMPOSITION_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String title = rs.getString("title");
                    compositions.add(new Composition(id, title));
                }
            } finally {
                ConnectionPool.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return compositions;
    }

    @Override
    public List<Composition> getAllCompositionBy(Predicate<Composition> predicate) {
        List<Composition> compositionsList = getAll();
        compositionsList = compositionsList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return compositionsList;
    }

    @Override
    public List<Authors> findAuthorsByCompositionId(Long authorsId) {
        List<Authors> authorsByComposition = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_AUTHORS_BY_COMPOSITION_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    authorsByComposition.add(new Authors(firstName, lastName));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return authorsByComposition;
    }

    @Override
    public List<Program> findProgramsByCompositionId(Long compositionId) {
        List<Program> programByComposition = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_PROGRAM_BY_COMPOSITION_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String programTitle = rs.getString("title");
                    String programDescription = rs.getString("description");
                    Long programOrganizationId = rs.getLong("organization_id");
                    String programAgeLimit = rs.getString("age_limit");
                    double programBasePrice = rs.getDouble("base_price");
                    programByComposition.add(new Program(Program.getId(), programTitle, programDescription, programOrganizationId, programAgeLimit, programBasePrice));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return programByComposition;
    }

    @Override
    public Composition getEntityById(Long id) {
        Composition composition = new Composition();
        try (PreparedStatement ps = connection.prepareStatement(GET_COMPOSITION_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    composition.setId(rs.getLong(1));
                    composition.setTitle(rs.getString(2));
                    System.out.println(Composition.getId() + "," + composition.getTitle());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return composition;
    }

    @Override
    public List<Composition> updateEntity(Composition entity) {
        List<Composition> updatedComposition = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_COMPOSITION_QUERY)) {
            ps.setString(1, entity.getTitle());
            ps.setLong(2, Composition.getId());
            ps.executeUpdate();
            updatedComposition = getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedComposition;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_COMPOSITION_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }
}
