package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.Authors;

import java.util.List;
import java.util.function.Predicate;

public interface IAuthorsDAO extends IBaseDAO <Authors, Long> {
    List  <Authors> getAllAuthors() throws Exception;
    List <Authors> getAllAuthorsBy (Predicate <Authors> predicate) throws Exception;
}
