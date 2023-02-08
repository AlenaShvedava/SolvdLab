package pl.solvd.concerthall.services;

import pl.solvd.concerthall.dao.impl.PosterDAOImpl;
import pl.solvd.concerthall.dao.impl.ProgramDAOImpl;
import pl.solvd.concerthall.dao.interfacesDAO.IPosterDAO;
import pl.solvd.concerthall.dao.interfacesDAO.IProgramDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Poster;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public class PosterService extends MySqlDAO implements IPosterDAO {
    private final IPosterDAO posterDAO = new PosterDAOImpl();
    private final IProgramDAO programDAO = new ProgramDAOImpl();

    @Override
    public Poster addEntity(Poster poster) throws Exception {
        Poster po = new Poster();
        po.setYear(poster.getYear());
        po.setMonth(poster.getMonth());
        po.setDay(poster.getDay());
        po.setTime(poster.getTime());
        Poster createdPoster= this.posterDAO.addEntity(po);
        return poster;
    }

    @Override
    public List<Poster> getAll() throws Exception {
        return posterDAO.getAll();
    }

    @Override
    public List<Poster> getAllPosterBy(Predicate<Poster> predicate) throws Exception {
        return posterDAO.getAllPosterBy(predicate);
    }

    @Override
    public Poster getEntityById(Long posterId) throws Exception {
        Poster poster = posterDAO.getEntityById(posterId);
        poster.setProgram(programDAO.findProgramByPosterId(posterId));
        return poster;
    }

    @Override
    public List<Poster> findPosterByProgramId(Long programId) throws SQLException {
        return posterDAO.findPosterByProgramId(programId);
    }

    @Override
    public List<Poster> updateEntity(Poster poster) throws Exception {
        Poster po = new Poster();
        po.setYear(poster.getYear());
        po.setMonth(poster.getMonth());
        po.setDay(poster.getDay());
        po.setTime(poster.getTime());
        po.setId(Poster.getId());
        return posterDAO.updateEntity(po);
    }

    @Override
    public void deleteEntity(Long id) throws Exception {
        posterDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }
}
