package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.DAO.impl.PosterDAOImpl;
import pl.solvd.concerthall.DAO.impl.ProgramDAOImpl;
import pl.solvd.concerthall.DAO.interfacesDAO.IPosterDAO;
import pl.solvd.concerthall.DAO.interfacesDAO.IProgramDAO;
import pl.solvd.concerthall.binary.Poster;
import pl.solvd.concerthall.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public class PosterService implements IPosterDAO {
    private final IPosterDAO posterDAO = new PosterDAOImpl();
    private final IProgramDAO programDAO = new ProgramDAOImpl();

    @Override
    public Poster addEntity(Poster poster) {
        Poster po = new Poster();
        po.setYear(poster.getYear());
        po.setMonth(poster.getMonth());
        po.setDay(poster.getDay());
        po.setTime(poster.getTime());
        Poster createdPoster= this.posterDAO.addEntity(po);
        return poster;
    }

    @Override
    public List<Poster> getAll() {
        return posterDAO.getAll();
    }

    @Override
    public List<Poster> getAllPosterBy(Predicate<Poster> predicate) {
        return posterDAO.getAllPosterBy(predicate);
    }

    @Override
    public Poster getEntityById(Long posterId) {
        Poster poster = posterDAO.getEntityById(posterId);
        poster.setProgram(posterDAO.findProgramsByPosterId(posterId));
        return poster;
    }

    @Override
    public List<Program> findProgramsByPosterId(Long programId) {
        return posterDAO.findProgramsByPosterId(programId);
    }

    @Override
    public List<Poster> updateEntity(Poster poster) {
        Poster po = new Poster();
        po.setYear(poster.getYear());
        po.setMonth(poster.getMonth());
        po.setDay(poster.getDay());
        po.setTime(poster.getTime());
        po.setId(Poster.getId());
        return posterDAO.updateEntity(po);
    }

    @Override
    public void deleteEntity(Long id) {
        posterDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }
}
