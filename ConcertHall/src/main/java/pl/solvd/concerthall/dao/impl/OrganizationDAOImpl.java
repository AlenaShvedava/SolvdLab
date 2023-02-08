package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IOrganizationDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Organization;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OrganizationDAOImpl extends MySqlDAO implements IOrganizationDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();

    private static final String GET_ALL_ORGANIZATION_QUERY = "SELECT * FROM organization";
    private static final String GET_ORGANIZATION_QUERY = "SELECT * FROM organization WHERE id = ?";
    private static final String INSERT_ORGANIZATION_QUERY = "INSERT INTO organization (name) VALUES(?)";
    private static final String UPDATE_ORGANIZATION_QUERY = "UPDATE organization SET name = ? WHERE id = ?";
    private static final String DELETE_ORGANIZATION_QUERY = "DELETE FROM organization WHERE id = ?";

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
    public List<Organization> getAll() throws Exception {
        List<Organization> organization = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_ORGANIZATION_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String name = rs.getString("name");
                    organization.add(new Organization(id, name));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool.close();
            }
            return organization;
        }
    }

    @Override
    public List<Organization> getAllOrganizationBy(Predicate<Organization> predicate) throws Exception {
        List<Organization> organizationList = getAll();
        organizationList = organizationList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return organizationList;
    }

    @Override
    public Organization getEntityById(Long id) throws Exception {
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
    public List<Organization> updateEntity(Organization entity) throws Exception {
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
