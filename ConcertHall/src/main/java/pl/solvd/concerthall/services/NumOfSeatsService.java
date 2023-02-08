package pl.solvd.concerthall.services;

import pl.solvd.concerthall.dao.impl.NumOfSeatsDAOImpl;
import pl.solvd.concerthall.dao.interfacesDAO.INumOfSeatsDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.NumOfSeats;

import java.util.List;
import java.util.function.Predicate;

public class NumOfSeatsService extends MySqlDAO implements INumOfSeatsDAO {
    private final INumOfSeatsDAO numOfSeatsDAO = new NumOfSeatsDAOImpl();

    @Override
    public NumOfSeats addEntity(NumOfSeats numOfSeats) throws Exception {
        NumOfSeats n = new NumOfSeats();
        n.setConcertHallsId(numOfSeats.getConcertHallsId());
        n.setPriceLevelId(numOfSeats.getPriceLevelId());
        n.setAmountOfSeats(numOfSeats.getAmountOfSeats());
        NumOfSeats createdNumOfSeats = this.numOfSeatsDAO.addEntity(n);
        return numOfSeats;
    }

    @Override
    public List<NumOfSeats> getAllNumOfSeatsBy(Predicate<NumOfSeats> predicate) throws Exception {
        return numOfSeatsDAO.getAllNumOfSeatsBy(predicate);
    }

    @Override
    public NumOfSeats getEntityById(Long numOfSeatsId) throws Exception {
        return numOfSeatsDAO.getEntityById(numOfSeatsId);
    }

    @Override
    public List<NumOfSeats> updateEntity(NumOfSeats numOfSeats) throws Exception {
        NumOfSeats n = new NumOfSeats();
        n.setConcertHallsId(numOfSeats.getConcertHallsId());
        n.setPriceLevelId(numOfSeats.getPriceLevelId());
        n.setAmountOfSeats(numOfSeats.getAmountOfSeats());
        n.setId(NumOfSeats.getId());
        return numOfSeatsDAO.updateEntity(n);
    }

    @Override
    public void deleteEntity(Long id) throws Exception {
        numOfSeatsDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<NumOfSeats> getAll() throws Exception {
        return numOfSeatsDAO.getAll();
    }
}
