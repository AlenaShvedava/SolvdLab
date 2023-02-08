package pl.solvd.concerthall.services;

import pl.solvd.concerthall.dao.impl.ImagesDAOImpl;
import pl.solvd.concerthall.dao.interfacesDAO.IImagesDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Images;

import java.util.List;
import java.util.function.Predicate;

public class ImagesService extends MySqlDAO implements IImagesDAO {
    private final IImagesDAO imagesDAO = new ImagesDAOImpl();

    @Override
    public Images addEntity(Images images) throws Exception {
        Images im = new Images();
        im.setImagePath(images.getImagePath());
        im.setProgramId(images.getProgramId());
        im.setPrimary(images.isPrimary());
        Images createdImages = this.imagesDAO.addEntity(im);
        return images;
    }

    @Override
    public List<Images> getAllImagesBy(Predicate<Images> predicate) throws Exception {
        return imagesDAO.getAllImagesBy(predicate);
    }

    @Override
    public Images getEntityById(Long imagesId) throws Exception {
        return imagesDAO.getEntityById(imagesId);
    }

    @Override
    public List<Images> updateEntity(Images images) throws Exception {
        Images image = new Images();
        image.setImagePath(images.getImagePath());
        image.setProgramId(images.getProgramId());
        image.setPrimary(images.isPrimary());
        image.setId(Images.getId());
        return imagesDAO.updateEntity(image);
    }

    @Override
    public void deleteEntity(Long id) throws Exception {
        imagesDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Images> getAll() throws Exception {
        return imagesDAO.getAll();
    }
}
