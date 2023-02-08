package pl.solvd.concerthall.services;

import pl.solvd.concerthall.dao.impl.CompositionDAOImpl;
import pl.solvd.concerthall.dao.impl.ConcertHallsDAOImpl;
import pl.solvd.concerthall.dao.impl.ProgramDAOImpl;
import pl.solvd.concerthall.dao.interfacesDAO.ICompositionDAO;
import pl.solvd.concerthall.dao.interfacesDAO.IConcertHallsDAO;
import pl.solvd.concerthall.dao.interfacesDAO.IProgramDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Program;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public class ProgramService extends MySqlDAO implements IProgramDAO {
    private final IProgramDAO programDAO = new ProgramDAOImpl();
    private final IConcertHallsDAO chDAO = new ConcertHallsDAOImpl();
    private final ICompositionDAO compositionDAO = new CompositionDAOImpl();

    @Override
    public Program addEntity(Program program) throws Exception {
        Program p = new Program();
        p.setTitle(program.getTitle());
        p.setDescription (program.getDescription());
        p.setOrganizationId (program.getOrganizationId());
        p.setAgeLimit(program.getAgeLimit());
        p.setBasePrice(program.getBasePrice());
        Program createdProgram= this.programDAO.addEntity(p);
        return program;
    }

    @Override
    public List<Program> getAllProgramBy(Predicate<Program> predicate) throws Exception {
        return programDAO.getAllProgramBy(predicate);
    }

    @Override
    public Program getEntityById(Long programId) throws Exception {
        Program program = programDAO.getEntityById(programId);
        program.setConcertHall(chDAO.findConcertHallsByProgramId(programId));
        program.setComposition(compositionDAO.findCompositionByAuthorsId(programId));
        return program;
    }

    @Override
    public List<Program> findProgramByConcertHallsId(Long concertHallsId) throws SQLException {
        return programDAO.findProgramByConcertHallsId(concertHallsId);
    }

    @Override
    public List<Program> findProgramByCompositionId(Long compositionId) throws SQLException {
        return programDAO.findProgramByCompositionId(compositionId);
    }

    @Override
    public List<Program> findProgramByGenreId(Long genreId) throws SQLException {
        return programDAO.findProgramByGenreId(genreId);
    }

    @Override
    public List<Program> findProgramByPosterId(Long posterId) throws SQLException {
        return programDAO.findProgramByPosterId(posterId);
    }

    @Override
    public List<Program> updateEntity(Program program) throws Exception {
        Program p = new Program();
        p.setTitle(program.getTitle());
        p.setDescription(program.getDescription());
        p.setOrganizationId(program.getOrganizationId());
        p.setAgeLimit(program.getAgeLimit());
        p.setBasePrice(program.getBasePrice());
        p.setId(Program.getId());
        return programDAO.updateEntity(p);
    }

    @Override
    public void deleteEntity(Long id) throws Exception {
        programDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Program> getAll() throws Exception {
        return programDAO.getAll();
    }
}
