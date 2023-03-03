package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.DAO.impl.AuthorTypesDAOImpl;
import pl.solvd.concerthall.DAO.impl.AuthorsDAOImpl;
import pl.solvd.concerthall.DAO.interfacesDAO.IAuthorTypesDAO;
import pl.solvd.concerthall.DAO.interfacesDAO.IAuthorsDAO;
import pl.solvd.concerthall.binary.AuthorTypes;
import pl.solvd.concerthall.binary.Authors;
import pl.solvd.concerthall.binary.Composition;

import java.util.List;
import java.util.function.Predicate;

public class AuthorsService implements IAuthorsDAO {
    private static final IAuthorsDAO authorDAO = new AuthorsDAOImpl();
    private static final IAuthorTypesDAO authorTypeDAO = new AuthorTypesDAOImpl();

    @Override
    public Authors addEntity(Authors authors) {
        Authors author = new Authors();
        author.setFirstName(authors.getFirstName());
        author.setLastName(authors.getLastName());
        Authors createdAuthor = authorDAO.addEntity(author);
        return authors;
    }

    @Override
    public List<Authors> getAllAuthorsBy(Predicate<Authors> predicate) throws Exception {
        return authorDAO.getAllAuthorsBy(predicate);
    }

    @Override
    public Authors getEntityById(Long authorsId) {
        Authors authors = authorDAO.getEntityById(authorsId);
       // authors.setAuthorTypes(authorDAO.findAuthorTypesByAuthorsId(authorsId));
        return authors;
    }

    @Override
    public List<AuthorTypes> findAuthorTypesByAuthorsId(Long authorsId) {
        return authorDAO.findAuthorTypesByAuthorsId(authorsId);
    }

    @Override
    public List<Composition> findCompositionsByAuthorsId(Long authorsId) {
        return authorDAO.findCompositionsByAuthorsId(authorsId);
    }

    @Override
    public List<Authors> updateEntity(Authors authors) {
        Authors author = new Authors();
        author.setFirstName(authors.getFirstName());
        author.setLastName(authors.getLastName());
        author.setId(authors.getId());
        return authorDAO.updateEntity(author);
    }

    @Override
    public void deleteEntity(Long id) {
        authorDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Authors> getAll() {
        return authorDAO.getAll();
    }
}
