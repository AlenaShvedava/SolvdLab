package pl.solvd.concerthall.DAO.impl;

import pl.solvd.concerthall.DAO.interfacesDAO.IOrganizationDAO;
import pl.solvd.concerthall.binary.Organization;
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

public class OrganizationDAOImpl implements IOrganizationDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();
    private static final String GET_ALL_ORGANIZATION_QUERY = "SELECT * FROM organization";
    private static final String GET_ORGANIZATION_QUERY = "SELECT * FROM organization WHERE id = ?";
    private static final String INSERT_ORGANIZATION_QUERY = "INSERT INTO organization (name) VALUES(?)";
    private static final String UPDATE_ORGANIZATION_QUERY = "UPDATE organization SET name = ? WHERE id = ?";
    private static final String DELETE_ORGANIZATION_QUERY = "DELETE FROM organization WHERE id = ?";
    private static final String GET_PROGRAMS_BY_ORGANIZATION_ID_QUERY = "SELECT program.title, program.description, program.age_limit, program.base_price FROM organization JOIN program ON organization.id = program.organization_id WHERE organization.id = ?";

    @Override
    public Organization addEntity(Organization entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_ORGANIZATION_QUERY)) {
            ps.setString(1, entity.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<Organization> getAll() {
        List<Organization> organization = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_ORGANIZATION_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String name = rs.getString("name");
                    organization.add(new Organization(id, name));
                }
            } finally {
                ConnectionPool.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return organization;
    }

    @Override
    public List<Organization> getAllOrganizationBy(Predicate<Organization> predicate) {
        List<Organization> organizationList = getAll();
        organizationList = organizationList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return organizationList;
    }

    @Override
    public Organization getEntityById(Long id) {
        Organization organization = new Organization();
        try (PreparedStatement ps = connection.prepareStatement(GET_ORGANIZATION_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    organization.setId(rs.getLong(1));
                    organization.setName(rs.getString(2));
                    System.out.println(Organization.getId() + "," + organization.getName());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return organization;
    }

    @Override
    public List<Program> findProgramsByOrganizationId(Long organizationId) {
        List<Program> programByOrganization = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_PROGRAMS_BY_ORGANIZATION_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String programTitle = rs.getString("title");
                    String programDescription = rs.getString("description");
                    Long programOrganization = rs.getLong("organization_id");
                    String programAgeLimit = rs.getString("age_limit");
                    double programBasePrice = rs.getDouble("base_price");
                    programByOrganization.add(new Program(Program.getId(), programTitle, programDescription, programOrganization, programAgeLimit, programBasePrice));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return programByOrganization;
    }

    @Override
    public List<Organization> updateEntity(Organization entity) {
        List<Organization> updatedOrganization = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ORGANIZATION_QUERY)) {
            ps.setString(1, entity.getName());
            ps.setLong(2, Organization.getId());
            ps.executeUpdate();
            updatedOrganization = getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedOrganization;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_ORGANIZATION_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }
}
