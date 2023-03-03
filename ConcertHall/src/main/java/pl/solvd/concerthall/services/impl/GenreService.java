package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.DAO.impl.GenreDAOImpl;
import pl.solvd.concerthall.DAO.impl.ProgramDAOImpl;
import pl.solvd.concerthall.DAO.interfacesDAO.IGenreDAO;
import pl.solvd.concerthall.DAO.interfacesDAO.IProgramDAO;
import pl.solvd.concerthall.binary.Events;
import pl.solvd.concerthall.binary.Genre;
import pl.solvd.concerthall.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public class GenreService implements IGenreDAO {
    private final IGenreDAO genreDAO = new GenreDAOImpl();
    private final IProgramDAO programDAO = new ProgramDAOImpl();

    @Override
    public Genre addEntity(Genre genre) {
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
    public Genre getEntityById(Long genreId) {
        Genre genre = genreDAO.getEntityById(genreId);
        genre.setProgram(genreDAO.findProgramsByGenreId(genreId));
        return genre;
    }

    @Override
    public List<Program> findProgramsByGenreId(Long programId) {
        return genreDAO.findProgramsByGenreId(programId);
    }

    @Override
    public List<Events> findEventsByGenreId(Long genreId) {
        return genreDAO.findEventsByGenreId(genreId);
    }

    @Override
    public List<Genre> updateEntity(Genre genre) {
        Genre g = new Genre();
        g.setType(genre.getType());
        g.setId(genre.getId());
        return genreDAO.updateEntity(g);
    }

    @Override
    public void deleteEntity(Long id) {
        genreDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Genre> getAll() {
        return genreDAO.getAll();
    }
}
