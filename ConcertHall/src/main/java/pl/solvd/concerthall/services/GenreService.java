package pl.solvd.concerthall.services;

import pl.solvd.concerthall.dao.impl.GenreDAOImpl;
import pl.solvd.concerthall.dao.impl.ProgramDAOImpl;
import pl.solvd.concerthall.dao.interfacesDAO.IGenreDAO;
import pl.solvd.concerthall.dao.interfacesDAO.IProgramDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Genre;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public class GenreService extends MySqlDAO implements IGenreDAO {
    private final IGenreDAO genreDAO = new GenreDAOImpl();
    private final IProgramDAO programDAO = new ProgramDAOImpl();

    @Override
    public Genre addEntity(Genre genre) throws Exception {
        Genre g = new Genre();
        g.setType(genre.getType());
        Genre createdGenre = this.genreDAO.addEntity(g);
        return genre;
    }

    @Override
    public List<Genre> getAllGenreBy(Predicate<Genre> predicate) throws Exception {
        return genreDAO.getAllGenreBy(predicate);
    }

    @Override
    public Genre getEntityById(Long genreId) throws Exception {
        Genre genre = genreDAO.getEntityById(genreId);
        genre.setProgram(programDAO.findProgramByGenreId(genreId));
        return genre;
    }

    @Override
    public List<Genre> findGenreByProgramId(Long programId) throws SQLException {
        return genreDAO.findGenreByProgramId(programId);
    }

    @Override
    public List<Genre> updateEntity(Genre genre) throws Exception {
        Genre g = new Genre();
        g.setType(genre.getType());
        g.setId(Genre.getId());
        return genreDAO.updateEntity(g);
    }

    @Override
    public void deleteEntity(Long id) throws Exception {
        genreDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Genre> getAll() throws Exception {
        return genreDAO.getAll();
    }
}
