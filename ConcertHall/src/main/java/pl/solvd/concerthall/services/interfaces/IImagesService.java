package pl.solvd.concerthall.services.interfaces;

import pl.solvd.concerthall.binary.Images;

import java.util.List;
import java.util.function.Predicate;

public interface IImagesService {
    Images addImage(Images Image);

    List<Images> getAllImagesBy(Predicate<Images> predicate);

    Images getImageById(Long imageId);

    List<Images> updateImage(Images Image);

    void deleteImage(Long id);

    List<Images> getAll();
}
