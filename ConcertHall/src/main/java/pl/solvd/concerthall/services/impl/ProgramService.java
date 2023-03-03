package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.DAO.impl.CompositionDAOImpl;
import pl.solvd.concerthall.DAO.impl.ConcertHallsDAOImpl;
import pl.solvd.concerthall.DAO.impl.ProgramDAOImpl;
import pl.solvd.concerthall.DAO.interfacesDAO.ICompositionDAO;
import pl.solvd.concerthall.DAO.interfacesDAO.IConcertHallsDAO;
import pl.solvd.concerthall.DAO.interfacesDAO.IProgramDAO;
import pl.solvd.concerthall.binary.*;

import java.util.List;
import java.util.function.Predicate;

public class ProgramService implements IProgramDAO {
    private final IProgramDAO programDAO = new ProgramDAOImpl();
    private final IConcertHallsDAO chDAO = new ConcertHallsDAOImpl();
    private final ICompositionDAO compositionDAO = new CompositionDAOImpl();

    @Override
    public Program addEntity(Program program) {
        Program p = new Program();
        p.setTitle(program.getTitle());
        p.setDescription(program.getDescription());
        p.setOrganizationId(program.getOrganizationId());
        p.setAgeLimit(program.getAgeLimit());
        p.setBasePrice(program.getBasePrice());
        Program createdProgram = this.programDAO.addEntity(p);
        return program;
    }

    @Override
    public List<Program> getAllProgramsBy(Predicate<Program> predicate) throws Exception {
        return programDAO.getAllProgramsBy(predicate);
    }

    @Override
    public Program getEntityById(Long programId) {
        Program program = programDAO.getEntityById(programId);
        program.setConcertHall(programDAO.findConcertHallByProgramId(programId));
        program.setComposition(programDAO.findCompositionByProgramId(programId));
        return program;
    }

    @Override
    public List<ConcertHalls> findConcertHallByProgramId(Long programId) {
        return programDAO.findConcertHallByProgramId(programId);
    }

    @Override
    public List<Composition> findCompositionByProgramId(Long programId) {
        return programDAO.findCompositionByProgramId(programId);
    }

    @Override
    public List<Genre> findGenreByProgramId(Long programId) {
        return programDAO.findGenreByProgramId(programId);
    }

    @Override
    public List<Poster> findPostersByProgramId(Long posterId) {
        return programDAO.findPostersByProgramId(posterId);
    }

    @Override
    public List<Images> findImagesByProgramId(Long programId) {
        return programDAO.findImagesByProgramId(programId);
    }

    @Override
    public List<Program> updateEntity(Program program) {
        Program p = new Program();
        p.setTitle(program.getTitle());
        p.setDescription(program.getDescription());
        p.setOrganizationId(program.getOrganizationId());
        p.setAgeLimit(program.getAgeLimit());
        p.setBasePrice(program.getBasePrice());
        p.setId(program.getId());
        return programDAO.updateEntity(p);
    }

    @Override
    public void deleteEntity(Long id) {
        programDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Program> getAll() {
        return programDAO.getAll();
    }
}
