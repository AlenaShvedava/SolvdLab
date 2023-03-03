package pl.solvd.concerthall.DAO.impl;

import pl.solvd.concerthall.DAO.interfacesDAO.IProgramDAO;
import pl.solvd.concerthall.binary.*;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgramDAOImpl implements IProgramDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static final Connection connection = instance.getConnection();

    private static final String GET_ALL_PROGRAM_QUERY = "SELECT * FROM program";
    private static final String GET_PROGRAM_QUERY = "SELECT * FROM program WHERE id = ?";
    private static final String INSERT_PROGRAM_QUERY = "INSERT INTO program (title, description, organization_id, age_limit, base_price) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE_PROGRAM_QUERY = "UPDATE program SET title = ?, description = ?, organization_id = ?, age_limit = ?, base_price = ? WHERE id = ?";
    private static final String DELETE_PROGRAM_QUERY = "DELETE FROM program WHERE id = ?";
    private static final String GET_COMPOSITION_BY_PROGRAM_ID_QUERY = "SELECT composition.title FROM program p JOIN program_has_composition ON program.id = program_has_composition.program_id JOIN composition ON program_has_composition.composition_id = composition.id where program.id = ?";
    private static final String GET_CONCERT_HALLS_BY_PROGRAM_QUERY = "SELECT concert_halls.name, concert_halls.address, concert_halls.phone FROM program JOIN program_has_concert_halls ON program.id = program_has_concert_halls.program_id JOIN concert_halls ON program_has_concert_halls.concert_halls_id = concert_halls.id WHERE program.id = ?";
    private static final String GET_POSTER_BY_PROGRAM_ID_QUERY = "SELECT poster.day, poster.month, poster.year, poster.time FROM program JOIN poster_has_program ON program.id = poster_has_program.program_id JOIN poster ON poster_has_program.poster_id = poster.id WHERE program.id = ?";
    private static final String GET_GENRE_BY_PROGRAM_ID_QUERY = "SELECT genre.type FROM program JOIN program_has_genre ON program.id =program_has_genre.program_id JOIN genre ON program_has_genre.genre_id = genre.id WHERE program.id = ?";
    private static final String GET_IMAGES_BY_PROGRAM_ID_QUERY = "SELECT image_path, images.isPrimary FROM program JOIN images ON program.id = images.program_id WHERE program.id = ?";
    @Override
    public Program addEntity(Program entity) {
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
    public List<Program> getAll() {
        List<Program> program = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_PROGRAM_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    Long organizationId = rs.getLong("organization_id");
                    String ageLimit = rs.getString("age_limit");
                    double basePrice = rs.getDouble("base_price");
                    program.add(new Program(id, title, description, organizationId, ageLimit, basePrice));
                }
            } finally {
                ConnectionPool.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return program;
    }

    @Override
    public List<Poster> findPostersByProgramId(Long programId) {
        List<Poster> posterByProgram = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_POSTER_BY_PROGRAM_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long posterId = rs.getLong("id");
                    int posterYear = rs.getInt("year");
                    int posterMonth = rs.getInt("month");
                    int posterDay = rs.getInt("day");
                    double posterTime = rs.getDouble("time");
                    posterByProgram.add(new Poster(posterId, posterYear, posterMonth, posterDay, posterTime));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return posterByProgram;
    }

    @Override
    public List<Program> getAllProgramsBy(Predicate<Program> predicate) {
        List<Program> programList = getAll();
        programList = programList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return programList;
    }

    @Override
    public List<ConcertHalls> findConcertHallByProgramId(Long programId) {
        List<ConcertHalls> concertHallsByProgram = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_CONCERT_HALLS_BY_PROGRAM_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String concertHallsName = rs.getString("name");
                    String concertHallsAddress = rs.getString("address");
                    String concertHallsPhone = rs.getString("phone");
                    int concertHallsSumNumberOfSeats = rs.getInt("sumNumber_of_seats");
                    concertHallsByProgram.add(new ConcertHalls(ConcertHalls.getId(), concertHallsName, concertHallsAddress, concertHallsPhone, concertHallsSumNumberOfSeats));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return concertHallsByProgram;
    }

    @Override
    public List<Genre> findGenreByProgramId(Long programId) {
        List<Genre> genreByProgram = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_GENRE_BY_PROGRAM_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String genreType = rs.getString("type");
                    genreByProgram.add(new Genre(Genre.getId(), genreType));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return genreByProgram;
    }

    @Override
    public List<Images> findImagesByProgramId(Long programId) {
        List<Images> imagesByProgram = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_IMAGES_BY_PROGRAM_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String imagePath = rs.getString("image_Path");
                    boolean isPrimary = rs.getBoolean("isPrimary");
                    imagesByProgram.add(new Images(Images.getId(), imagePath, programId, isPrimary));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return imagesByProgram;
    }

    @Override
    public Program getEntityById(Long id) {
        Program program = new Program();
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
                    System.out.println(Program.getId() + "," + program.getTitle() + "," + program.getDescription() + program.getOrganizationId() + "," + program.getAgeLimit() + "," + program.getBasePrice());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return program;
    }

    @Override
    public List<Program> updateEntity(Program entity) {
        List<Program> updatedProgram = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PROGRAM_QUERY)) {
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getDescription());
            ps.setLong(3, entity.getOrganizationId());
            ps.setString(4, entity.getAgeLimit());
            ps.setDouble(5, entity.getBasePrice());
            ps.setLong(6, Program.getId());
            ps.executeUpdate();
            updatedProgram = getAll();
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

    @Override
    public List<Composition> findCompositionByProgramId(Long programId) {
        List<Composition> compositionByProgram = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_COMPOSITION_BY_PROGRAM_ID_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String compositionTitle = rs.getString("title");
                    compositionByProgram.add(new Composition(Composition.getId(), compositionTitle));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return compositionByProgram;
    }
}
