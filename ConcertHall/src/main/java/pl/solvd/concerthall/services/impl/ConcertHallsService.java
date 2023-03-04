package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.DAO.impl.ConcertHallsDAOImpl;
import pl.solvd.concerthall.DAO.impl.ProgramDAOImpl;
import pl.solvd.concerthall.DAO.interfacesDAO.IConcertHallsDAO;
import pl.solvd.concerthall.DAO.interfacesDAO.IProgramDAO;
import pl.solvd.concerthall.binary.ConcertHalls;
import pl.solvd.concerthall.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public class ConcertHallsService implements IConcertHallsDAO {
    private final IConcertHallsDAO chDAO = new ConcertHallsDAOImpl();
    private final IProgramDAO programDAO = new ProgramDAOImpl();

    @Override
    public ConcertHalls addEntity(ConcertHalls ch) {
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
    public List<Program> findProgramsByConcertHallId(Long concertHallId) {
        return chDAO.findProgramsByConcertHallId(concertHallId);
    }

    @Override
    public ConcertHalls getEntityById(Long concertHallsId) {
        ConcertHalls concertHalls = chDAO.getEntityById(concertHallsId);
        concertHalls.setProgram(chDAO.findProgramsByConcertHallId(concertHallsId));
        return concertHalls;
    }

    @Override
    public List<ConcertHalls> updateEntity(ConcertHalls chs) {
        ConcertHalls concertHalls = new ConcertHalls();
        concertHalls.setName(chs.getName());
        concertHalls.setAddress(chs.getAddress());
        concertHalls.setPhone(chs.getPhone());
        concertHalls.setSumNumberOfSeats(chs.getSumNumberOfSeats());
        concertHalls.setId(ConcertHalls.getId());
        return chDAO.updateEntity(concertHalls);
    }

    @Override
    public void deleteEntity(Long id) {
        chDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<ConcertHalls> getAll() {
        return chDAO.getAll();
    }
}
