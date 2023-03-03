package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.DAO.impl.AuthorTypesDAOImpl;
import pl.solvd.concerthall.DAO.impl.AuthorsDAOImpl;
import pl.solvd.concerthall.DAO.interfacesDAO.IAuthorTypesDAO;
import pl.solvd.concerthall.DAO.interfacesDAO.IAuthorsDAO;
import pl.solvd.concerthall.binary.AuthorTypes;
import pl.solvd.concerthall.binary.Authors;

import java.util.List;
import java.util.function.Predicate;

public class AuthorTypesService implements IAuthorTypesDAO {
    private final IAuthorTypesDAO authorTypesDAO = new AuthorTypesDAOImpl();
    private final IAuthorsDAO authorsDAO = new AuthorsDAOImpl();

    @Override
    public AuthorTypes addEntity(AuthorTypes authorTypes) {
        AuthorTypes authorType = new AuthorTypes();
        authorType.setType(authorTypes.getType());
        AuthorTypes createdAuthorType = this.authorTypesDAO.addEntity(authorType);
        return authorTypes;
    }

    @Override
    public AuthorTypes getEntityById(Long authorTypesId) {
        AuthorTypes authorType = authorTypesDAO.getEntityById(authorTypesId);
        authorType.setAuthors(authorTypesDAO.findAuthorsByAuthorTypesId(authorTypesId));
        return authorType;
    }

    @Override
    public List<Authors> findAuthorsByAuthorTypesId(Long authorsId) {
        return authorTypesDAO.findAuthorsByAuthorTypesId(authorsId);
    }

    @Override
    public List<AuthorTypes> updateEntity(AuthorTypes authorTypes) {
        AuthorTypes authorType = new AuthorTypes();
        authorType.setType(authorTypes.getType());
        authorType.setId(authorTypes.getId());
        return authorTypesDAO.updateEntity(authorType);
    }

    @Override
    public void deleteEntity(Long id) {
        authorTypesDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<AuthorTypes> getAllAuthorTypesBy(Predicate<AuthorTypes> predicate) throws Exception {
        return authorTypesDAO.getAllAuthorTypesBy(predicate);
    }

    @Override
    public List<AuthorTypes> getAll() {
        return authorTypesDAO.getAll();
    }
}
