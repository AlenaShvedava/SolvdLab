package pl.solvd.concerthall.services;

import pl.solvd.concerthall.dao.impl.AuthorTypesDAOImpl;
import pl.solvd.concerthall.dao.impl.AuthorsDAOImpl;
import pl.solvd.concerthall.dao.interfacesDAO.IAuthorTypesDAO;
import pl.solvd.concerthall.dao.interfacesDAO.IAuthorsDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Authors;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

import static pl.solvd.concerthall.App.LOG;

public class AuthorsService extends MySqlDAO implements IAuthorsDAO {
    private final IAuthorsDAO authorDAO = new AuthorsDAOImpl();
    private final IAuthorTypesDAO authorTypeDAO = new AuthorTypesDAOImpl();

    @Override
    public Authors addEntity(Authors authors) throws Exception {
        Authors author = new Authors();
        author.setFirstName(authors.getFirstName());
        author.setLastName(authors.getLastName());
        Authors createdAuthor = this.authorDAO.addEntity(author);
        return authors;
    }

    @Override
    public List<Authors> getAllAuthorsBy(Predicate<Authors> predicate) throws Exception {
        return authorDAO.getAllAuthorsBy(predicate);
    }

    @Override
    public Authors getEntityById(Long authorsId) throws Exception {
        Authors authors = authorDAO.getEntityById(authorsId);
        authors.setAuthorTypes(authorTypeDAO.findAuthorsTypesByAuthorsId(authorsId));
        return authors;
    }

    @Override
    public List<Authors> findAuthorsByAuthorTypesId(Long authorTypesId) throws SQLException {
        return authorDAO.findAuthorsByAuthorTypesId(authorTypesId);
    }

    @Override
    public List<Authors> findAuthorsByCompositionId(Long compositionId) throws SQLException {
        return authorDAO.findAuthorsByCompositionId(compositionId);
    }

    @Override
    public List<Authors> updateEntity(Authors authors) throws Exception {
        Authors author = new Authors();
        author.setFirstName(authors.getFirstName());
        author.setLastName(authors.getLastName());
        author.setId(Authors.getId());
        return authorDAO.updateEntity(author);
    }

    @Override
    public void deleteEntity(Long id) throws Exception {
        authorDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Authors> getAll() throws Exception {
        return authorDAO.getAll();
    }
}
