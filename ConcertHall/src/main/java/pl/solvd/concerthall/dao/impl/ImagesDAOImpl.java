package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IImagesDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsEntity;
import pl.solvd.concerthall.entities.ImagesEntity;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ImagesDAOImpl extends MySqlDAO implements IImagesDAO {
    private static final ConnectionPool instance = ConnectionPool.getInstance();
    private static Connection connection = instance.getConnection();

    private static final String GET_ALL_IMAGES_QUERY = "SELECT * FROM images";
    private static final String GET_IMAGES_QUERY = "SELECT * FROM images WHERE id = ?";
    private static final String INSERT_IMAGES_QUERY = "INSERT INTO images(image_path, program_id, isPrimary) VALUES(?, ?, ?)";
    private static final String UPDATE_IMAGES_QUERY = "UPDATE images SET image_path = ?, program_id = ?, isPrimary = ? WHERE id = ?";
    private static final String DELETE_IMAGES_QUERY = "DELETE FROM images WHERE id = ?";

    @Override
    public ImagesEntity saveEntity(ImagesEntity entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_IMAGES_QUERY)) {
            ps.setString(1, entity.getImagePath());
            ps.setLong(2, entity.getProgramId());
            ps.setBoolean(3, entity.isPrimary());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return entity;
    }

    @Override
    public List<ImagesEntity> getAllImages() throws Exception {
        List<ImagesEntity> images = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_IMAGES_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String imagePath = rs.getString("image_path");
                    Long programId = rs.getLong("program_id");
                    Boolean isPrimary = rs.getBoolean("isPrimary");
                    images.add(new ImagesEntity(id, imagePath, programId, isPrimary));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool.close();
            }
            return images;
        }
    }

    @Override
    public List<ImagesEntity> getAllImagesBy(Predicate<ImagesEntity> predicate) throws Exception {
        List<ImagesEntity> imagesList = getAllImages();
        imagesList = imagesList.stream().filter(predicate).collect(Collectors.toList());
        ConnectionPool.close();
        return imagesList;
    }

    @Override
    public void getEntityById(Long id) throws Exception {
        ImagesEntity images = new ImagesEntity();
        try (PreparedStatement ps = connection.prepareStatement(GET_IMAGES_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    images.setId(rs.getLong(1));
                    images.setImagePath(rs.getString(2));
                    images.setProgramId(rs.getLong(3));
                    images.setPrimary(rs.getBoolean(4));
                    System.out.println(images.getId() + "," + images.getImagePath() + "," + images.getProgramId() + "," + images.isPrimary());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }

    @Override
    public List<ImagesEntity> updateEntity(ImagesEntity entity) throws Exception {
        List<ImagesEntity> updatedImages = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_IMAGES_QUERY)) {
            ps.setString(1, entity.getImagePath());
            ps.setLong(2, entity.getProgramId());
            ps.setBoolean(2, entity.isPrimary());
            ps.setLong(3, entity.getId());
            ps.executeUpdate();
            updatedImages = getAllImages();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.close();
        }
        return updatedImages;
    }

    @Override
    public void deleteEntity(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_IMAGES_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionPool.close();
        }
    }
}
