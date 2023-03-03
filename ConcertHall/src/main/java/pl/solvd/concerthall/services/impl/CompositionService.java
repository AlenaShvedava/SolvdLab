package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.DAO.impl.AuthorsDAOImpl;
import pl.solvd.concerthall.DAO.impl.CompositionDAOImpl;
import pl.solvd.concerthall.DAO.interfacesDAO.IAuthorsDAO;
import pl.solvd.concerthall.DAO.interfacesDAO.ICompositionDAO;
import pl.solvd.concerthall.binary.Authors;
import pl.solvd.concerthall.binary.Composition;
import pl.solvd.concerthall.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public class CompositionService implements ICompositionDAO {
    private final ICompositionDAO compositionDAO = new CompositionDAOImpl();
    private final IAuthorsDAO authorsDAO = new AuthorsDAOImpl();


    @Override
    public Composition addEntity(Composition c) {
        Composition composition = new Composition();
        composition.setTitle(c.getTitle());
        Composition createdComposition = this.compositionDAO.addEntity(composition);
        return c;
    }

    @Override
    public List<Composition> getAllCompositionBy(Predicate<Composition> predicate) {
        return compositionDAO.getAllCompositionBy(predicate);
    }

    @Override
    public List<Authors> findAuthorsByCompositionId(Long authorsId) {
        return compositionDAO.findAuthorsByCompositionId((authorsId));
    }

    @Override
    public List<Program> findProgramsByCompositionId(Long programId) {
        return compositionDAO.findProgramsByCompositionId(programId);
    }

    @Override
    public Composition getEntityById(Long compositionId) {
        Composition composition = compositionDAO.getEntityById(compositionId);
        composition.setAuthors(compositionDAO.findAuthorsByCompositionId(compositionId));
        return composition;
    }

    @Override
    public List<Composition> updateEntity(Composition c) {
        Composition composition = new Composition();
        composition.setTitle(c.getTitle());
        composition.setId(composition.getId());
        return compositionDAO.updateEntity(composition);
    }

    @Override
    public void deleteEntity(Long id) {
        compositionDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Composition> getAll() {
        return compositionDAO.getAll();
    }
}
