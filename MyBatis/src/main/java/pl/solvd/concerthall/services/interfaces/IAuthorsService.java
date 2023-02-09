package pl.solvd.concerthall.services.interfaces;

import pl.solvd.concerthall.binary.Authors;

import java.util.List;
import java.util.function.Predicate;

public interface IAuthorsService {
    Authors addEntity(Authors authors);
    List<Authors> getAllAuthorsBy(Predicate<Authors> predicate);
    Authors getEntityById(Long authorsId);
    void findAuthorsByAuthorTypesId(Long authorTypesId);
    public void findAuthorsByCompositionId(Long compositionId);
    List<Authors> updateEntity(Authors authors);
    void deleteEntity(Long id);
    List<Authors> getAll();
}
