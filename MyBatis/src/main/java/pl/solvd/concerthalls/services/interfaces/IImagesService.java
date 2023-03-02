package pl.solvd.concerthalls.services.interfaces;

import pl.solvd.concerthalls.binary.Images;

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
