package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.DAO.impl.NumOfSeatsDAOImpl;
import pl.solvd.concerthall.DAO.interfacesDAO.INumOfSeatsDAO;
import pl.solvd.concerthall.binary.NumOfSeats;

import java.util.List;
import java.util.function.Predicate;

public class NumOfSeatsService implements INumOfSeatsDAO {
    private final INumOfSeatsDAO numOfSeatsDAO = new NumOfSeatsDAOImpl();

    @Override
    public NumOfSeats addEntity(NumOfSeats numOfSeats) {
        NumOfSeats n = new NumOfSeats();
        n.setConcertHallsId(numOfSeats.getConcertHallsId());
        n.setPriceLevelId(numOfSeats.getPriceLevelId());
        n.setAmountOfSeats(numOfSeats.getAmountOfSeats());
        NumOfSeats createdNumOfSeats = this.numOfSeatsDAO.addEntity(n);
        return numOfSeats;
    }

    @Override
    public List<NumOfSeats> getAllNumOfSeatsBy(Predicate<NumOfSeats> predicate) {
        return numOfSeatsDAO.getAllNumOfSeatsBy(predicate);
    }

    @Override
    public NumOfSeats getEntityById(Long numOfSeatsId) {
        return numOfSeatsDAO.getEntityById(numOfSeatsId);
    }

    @Override
    public List<NumOfSeats> updateEntity(NumOfSeats numOfSeats) {
        NumOfSeats n = new NumOfSeats();
        n.setConcertHallsId(numOfSeats.getConcertHallsId());
        n.setPriceLevelId(numOfSeats.getPriceLevelId());
        n.setAmountOfSeats(numOfSeats.getAmountOfSeats());
        n.setId(numOfSeats.getId());
        return numOfSeatsDAO.updateEntity(n);
    }

    @Override
    public void deleteEntity(Long id) {
        numOfSeatsDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<NumOfSeats> getAll() {
        return numOfSeatsDAO.getAll();
    }
}
