package pl.solvd.concerthall.services;

import pl.solvd.concerthall.dao.impl.AuthorTypesDAOImpl;
import pl.solvd.concerthall.dao.impl.AuthorsDAOImpl;
import pl.solvd.concerthall.dao.interfacesDAO.IAuthorTypesDAO;
import pl.solvd.concerthall.dao.interfacesDAO.IAuthorsDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorTypes;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public class AuthorTypesService extends MySqlDAO implements IAuthorTypesDAO {
    private final IAuthorTypesDAO authorTypesDAO = new AuthorTypesDAOImpl();
    private final IAuthorsDAO authorsDAO = new AuthorsDAOImpl();

    @Override
    public AuthorTypes addEntity(AuthorTypes authorTypes) throws Exception {
        AuthorTypes authorType = new AuthorTypes();
        authorType.setType(authorTypes.getType());
        AuthorTypes createdAuthorType = this.authorTypesDAO.addEntity(authorType);
        return authorTypes;
    }

    @Override
    public AuthorTypes getEntityById(Long authorTypesId) throws Exception {
        AuthorTypes authorType = authorTypesDAO.getEntityById(authorTypesId);
        authorType.setAuthors(authorsDAO.findAuthorsByAuthorTypesId(authorTypesId));
        return authorType;
    }

    @Override
    public List<AuthorTypes> findAuthorsTypesByAuthorsId(Long authorsId) throws SQLException {
        return authorTypesDAO.findAuthorsTypesByAuthorsId(authorsId);
    }

    @Override
    public List<AuthorTypes> updateEntity(AuthorTypes authorTypes) throws Exception {
        AuthorTypes authorType = new AuthorTypes();
        authorType.setType(authorTypes.getType());
        authorType.setId(AuthorTypes.getId());
        return authorTypesDAO.updateEntity(authorType);
    }

    @Override
    public void deleteEntity(Long id) throws Exception {
        authorTypesDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<AuthorTypes> getAllAuthorTypesBy(Predicate<AuthorTypes> predicate) throws Exception {
        return authorTypesDAO.getAllAuthorTypesBy(predicate);
    }

    @Override
    public List<AuthorTypes> getAll() throws Exception {
        return authorTypesDAO.getAll();
    }
}
