package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.dao.impl.AuthorTypesDAOImpl;
import pl.solvd.concerthall.dao.impl.AuthorsDAOImpl;
import pl.solvd.concerthall.dao.impl.AuthorsHasAuthorTypesDAOImpl;
import pl.solvd.concerthall.dao.interfacesDAO.IAuthorTypesDAO;
import pl.solvd.concerthall.dao.interfacesDAO.IAuthorsDAO;
import pl.solvd.concerthall.dao.interfacesDAO.IAuthorsHasAuthorTypesDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.AuthorsEntity;
import pl.solvd.concerthall.exceptions.ServiceException;
import pl.solvd.concerthall.models.Authors;
import pl.solvd.concerthall.services.AuthorsService;

public class AuthorsServiceImpl extends MySqlDAO implements AuthorsService {
    private final IAuthorsDAO a = new AuthorsDAOImpl();
    private final IAuthorTypesDAO aType = new AuthorTypesDAOImpl();
    private final IAuthorsHasAuthorTypesDAO aHasAType = new AuthorsHasAuthorTypesDAOImpl();

    @Override
    public Authors saveEntity(Authors authors) throws Exception {
        AuthorsEntity author = new AuthorsEntity();
        author.setFirstName(authors.getFirstName());
        author.setLastName(authors.getLastName());
        AuthorsEntity createdAuthors = this.a.saveEntity(author);
        return authors;
    }

    @Override
    public Authors getEntityById(Long id) throws Exception {
        return null;
    }

    @Override
    public void updateEntity(Authors authors) throws ServiceException {

    }

    @Override
    public String deleteEntity(Long id) throws ServiceException {
        return null;
    }
}
