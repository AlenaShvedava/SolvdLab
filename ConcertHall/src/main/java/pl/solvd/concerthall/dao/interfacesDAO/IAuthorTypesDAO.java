package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.AuthorTypesEntity;
import java.util.List;
import java.util.function.Predicate;

public interface IAuthorTypesDAO extends IBaseDAO <AuthorTypesEntity, Long> {
    List<AuthorTypesEntity> getAllAuthorTypes() throws Exception;
    List <AuthorTypesEntity> getAllAuthorTypesBy (Predicate <AuthorTypesEntity> predicate) throws Exception;

}
