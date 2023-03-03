package pl.solvd.concerthall.DAO.impl;

import pl.solvd.concerthall.DAO.interfacesDAO.IAuthorsDAO;
import pl.solvd.concerthall.binary.AuthorTypes;
import pl.solvd.concerthall.binary.Authors;
import pl.solvd.concerthall.binary.Composition;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AuthorsDAOImpl implements IAuthorsDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();
    private static final String GET_ALL_AUTHORS_QUERY = "SELECT * FROM authors";
    private static final String GET_AUTHOR_QUERY = "SELECT * FROM authors WHERE id = ?";
    private static final String INSERT_AUTHOR_QUERY = "INSERT INTO authors(first_name, last_name) VALUES(?, ?)";
    private static final String UPDATE_AUTHOR_QUERY = "UPDATE authors SET first_name = ?, last_name = ? WHERE id = ?";
    private static final String DELETE_AUTHOR_QUERY = "DELETE FROM authors WHERE id = ?";
    private static final String GET_AUTHOR_TYPES_BY_AUTHORS_ID_QUERY = " SELECT author_type.type FROM authors JOIN authors_has_author_types ON authors.id = authors_has_author_types.authors_id JOIN author_types ON authors_has_author_types.author_types_id = author_types.id where authors.id = ?";
    private static final String GET_COMPOSITION_BY_AUTHORS_QUERY = "SELECT composition.title FROM authors JOIN composition_has_authors ON authors.id = composition_has_authors.authors_id JOIN composition ON composition_has_authors.composition_id = composition.id WHERE authors.id = ?";


    @Override
    public Authors addEntity(Authors entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_AUTHOR_QUERY)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<Authors> getAll() {
        List<Authors> authors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_AUTHORS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    authors.add(new Authors(firstName, lastName));
                }
            } finally {
                ConnectionPool.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return authors;
    }

    @Override
    public List<Authors> getAllAuthorsBy(Predicate<Authors> predicate) {
        List<Authors> authorsList = getAll();
        authorsList = authorsList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return authorsList;
    }

    @Override
    public Authors getEntityById(Long id) {
        Authors author = new Authors();
        try (PreparedStatement ps = connection.prepareStatement(GET_AUTHOR_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    author.setId(rs.getLong(1));
                    author.setFirstName(rs.getString(2));
                    author.setLastName(rs.getString(3));
                    System.out.println(Authors.getId() + "," + author.getFirstName() + "," + author.getLastName());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return author;
    }

    @Override
    public List<Authors> updateEntity(Authors entity) {
        List<Authors> updatedAuthors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_AUTHOR_QUERY)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setLong(3, Authors.getId());
            ps.executeUpdate();
            updatedAuthors = getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedAuthors;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_AUTHOR_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }

    @Override
    public List<AuthorTypes> findAuthorTypesByAuthorsId(Long authorsId) {
        List<AuthorTypes> authorTypesByAuthor = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_AUTHOR_TYPES_BY_AUTHORS_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String authorType = rs.getString("type");
                    authorTypesByAuthor.add(new AuthorTypes(AuthorTypes.getId(), authorType));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return authorTypesByAuthor;
    }

    @Override
    public List<Composition> findCompositionsByAuthorsId(Long authorsId) {
        List<Composition> compositionByAuthor = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_COMPOSITION_BY_AUTHORS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String compositionTitle = rs.getString("title");
                    compositionByAuthor.add(new Composition(Composition.getId(), compositionTitle));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return compositionByAuthor;
    }
}
