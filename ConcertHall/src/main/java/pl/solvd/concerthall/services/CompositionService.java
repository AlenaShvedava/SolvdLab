package pl.solvd.concerthall.services;

import pl.solvd.concerthall.dao.impl.AuthorsDAOImpl;
import pl.solvd.concerthall.dao.impl.CompositionDAOImpl;
import pl.solvd.concerthall.dao.interfacesDAO.IAuthorsDAO;
import pl.solvd.concerthall.dao.interfacesDAO.ICompositionDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Composition;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public class CompositionService extends MySqlDAO implements ICompositionDAO {
    private final ICompositionDAO compositionDAO = new CompositionDAOImpl();
    private final IAuthorsDAO authorsDAO = new AuthorsDAOImpl();


    @Override
    public Composition addEntity(Composition c) throws Exception {
        Composition composition = new Composition();
        composition.setTitle(c.getTitle());
        Composition createdComposition = this.compositionDAO.addEntity(composition);
        return c;
    }

    @Override
    public List<Composition> getAllCompositionBy(Predicate<Composition> predicate) throws Exception {
        return compositionDAO.getAllCompositionBy(predicate);
    }

    @Override
    public List<Composition> findCompositionByAuthorsId(Long authorsId) throws SQLException {
        return compositionDAO.findCompositionByAuthorsId(authorsId);
    }

    @Override
    public List<Composition> findCompositionByProgramId(Long programId) throws SQLException {
        return compositionDAO.findCompositionByProgramId(programId);
    }

    @Override
    public Composition getEntityById(Long compositionId) throws Exception {
        Composition composition = compositionDAO.getEntityById(compositionId);
        composition.setAuthors(authorsDAO.findAuthorsByCompositionId(compositionId));
        return composition;
    }

    @Override
    public List<Composition> updateEntity(Composition c) throws Exception {
        Composition composition = new Composition();
        composition.setTitle(c.getTitle());
        composition.setId(Composition.getId());
        return compositionDAO.updateEntity(composition);
    }

    @Override
    public void deleteEntity(Long id) throws Exception {
        compositionDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Composition> getAll() throws Exception {
        return compositionDAO.getAll();
    }
}
