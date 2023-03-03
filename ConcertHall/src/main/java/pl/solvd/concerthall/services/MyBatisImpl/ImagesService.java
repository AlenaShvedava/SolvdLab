package pl.solvd.concerthall.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthall.DAO.interfacesDAO.IImagesDAO;
import pl.solvd.concerthall.binary.Images;
import pl.solvd.concerthall.services.interfaces.IImagesService;
import pl.solvd.concerthall.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ImagesService implements IImagesService {
    Logger LOG = LogManager.getLogger(ImagesService.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();

    @Override
    public Images addImage(Images image) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IImagesDAO imageDAO = session.getMapper(IImagesDAO.class);
            try {
                imageDAO.addEntity(image);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return image;
    }

    @Override
    public List<Images> getAllImagesBy(Predicate<Images> predicate) {
        return null;
    }

    @Override
    public Images getImageById(Long imageId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IImagesDAO imageDAO = session.getMapper(IImagesDAO.class);
            Images image = imageDAO.getEntityById(imageId);
            return image;
        }
    }

    @Override
    public List<Images> updateImage(Images image) {
        List<Images> updatedImage = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IImagesDAO imageDAO = session.getMapper(IImagesDAO.class);
            try {
                imageDAO.updateEntity(image);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return updatedImage;
    }

    @Override
    public void deleteImage(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IImagesDAO imageDAO = session.getMapper(IImagesDAO.class);
            try {
                imageDAO.deleteEntity(id);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Images> getAll() {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IImagesDAO imageDAO = session.getMapper(IImagesDAO.class);
            List<Images> list = imageDAO.getAll();
            return list;
        }
    }
}
