package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.AuthorsEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IAuthorsDAO extends IBaseDAO <AuthorsEntity, Long> {
    List  <AuthorsEntity> getAllAuthors() throws Exception;
    List <AuthorsEntity> getAllAuthorsBy (Predicate <AuthorsEntity> predicate) throws Exception;
}
