package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.DAO.impl.ImagesDAOImpl;
import pl.solvd.concerthall.DAO.interfacesDAO.IImagesDAO;
import pl.solvd.concerthall.binary.Images;

import java.util.List;
import java.util.function.Predicate;

public class ImagesService implements IImagesDAO {
    private final IImagesDAO imagesDAO = new ImagesDAOImpl();

    @Override
    public Images addEntity(Images images) {
        Images im = new Images();
        im.setImagePath(images.getImagePath());
        im.setProgramId(images.getProgramId());
        im.setPrimary(images.isPrimary());
        Images createdImages = this.imagesDAO.addEntity(im);
        return images;
    }

    @Override
    public List<Images> getAllImagesBy(Predicate<Images> predicate) {
        return imagesDAO.getAllImagesBy(predicate);
    }

    @Override
    public Images getEntityById(Long imagesId) {
        return imagesDAO.getEntityById(imagesId);
    }

    @Override
    public List<Images> updateEntity(Images images) {
        Images image = new Images();
        image.setImagePath(images.getImagePath());
        image.setProgramId(images.getProgramId());
        image.setPrimary(images.isPrimary());
        image.setId(images.getId());
        return imagesDAO.updateEntity(image);
    }

    @Override
    public void deleteEntity(Long id) {
        imagesDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Images> getAll() {
        return imagesDAO.getAll();
    }
}
