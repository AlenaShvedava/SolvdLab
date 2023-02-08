package pl.solvd.concerthall.services;

import pl.solvd.concerthall.dao.impl.ConcertHallsDAOImpl;
import pl.solvd.concerthall.dao.impl.ProgramDAOImpl;
import pl.solvd.concerthall.dao.interfacesDAO.IConcertHallsDAO;
import pl.solvd.concerthall.dao.interfacesDAO.IProgramDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.ConcertHalls;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public class ConcertHallsService extends MySqlDAO implements IConcertHallsDAO {
    private final IConcertHallsDAO chDAO = new ConcertHallsDAOImpl();
    private final IProgramDAO programDAO = new ProgramDAOImpl();

    @Override
    public ConcertHalls addEntity(ConcertHalls ch) throws Exception {
        ConcertHalls concertHalls = new ConcertHalls();
        concertHalls.setName(ch.getName());
        concertHalls.setAddress(ch.getAddress());
        concertHalls.setPhone(ch.getPhone());
        concertHalls.setSumNumberOfSeats(ch.getSumNumberOfSeats());
        ConcertHalls createdConcertHalls = this.chDAO.addEntity(concertHalls);
        return ch;
    }

    @Override
    public List<ConcertHalls> getAllConcertHallsBy(Predicate<ConcertHalls> predicate) throws Exception {
        return chDAO.getAllConcertHallsBy(predicate);
    }

    @Override
    public ConcertHalls getEntityById(Long concertHallsId) throws Exception {
        ConcertHalls concertHalls = chDAO.getEntityById(concertHallsId);
        concertHalls.setProgram(programDAO.findProgramByConcertHallsId(concertHallsId));
        return concertHalls;
    }

    @Override
    public List<ConcertHalls> findConcertHallsByProgramId(Long programId) throws SQLException {
        return chDAO.findConcertHallsByProgramId(programId);
    }

    @Override
    public List<ConcertHalls> updateEntity(ConcertHalls chs) throws Exception {
        ConcertHalls concertHalls = new ConcertHalls();
        concertHalls.setName(chs.getName());
        concertHalls.setAddress(chs.getAddress());
        concertHalls.setPhone(chs.getPhone());
        concertHalls.setSumNumberOfSeats(chs.getSumNumberOfSeats());
        concertHalls.setId(ConcertHalls.getId());
        return chDAO.updateEntity(concertHalls);
    }

    @Override
    public void deleteEntity(Long id) throws Exception {
        chDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<ConcertHalls> getAll() throws Exception {
        return chDAO.getAll();
    }
}
