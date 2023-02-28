package pl.solvd.concerthalls.services.interfaces;

import pl.solvd.concerthalls.binary.Authors;

import java.util.List;
import java.util.function.Predicate;

public interface IAuthorsService {
    Authors addEntity(Authors authors);
    List<Authors> getAllAuthorsBy(Predicate<Authors> predicate);
    Authors getEntityById(Long authorsId);
    List<Authors> findAuthorsByAuthorTypesId(Long authorTypesId);
//    public void findAuthorsByCompositionId(Long compositionId);
    List<Authors> updateEntity(Authors authors);
    void deleteEntity(Long id);
    List<Authors> getAll();
}
