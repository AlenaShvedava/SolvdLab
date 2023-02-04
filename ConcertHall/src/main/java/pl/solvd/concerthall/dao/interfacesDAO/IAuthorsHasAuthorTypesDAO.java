package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.AuthorsHasAuthorTypesEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IAuthorsHasAuthorTypesDAO extends IBaseDAO <AuthorsHasAuthorTypesEntity, Long>{
    List<AuthorsHasAuthorTypesEntity> getAllAuthorsHasAuthorTypes() throws Exception;
    List <AuthorsHasAuthorTypesEntity> getAllAuthorsHasAuthorTypesBy (Predicate<AuthorsHasAuthorTypesEntity> predicate) throws Exception;

}
