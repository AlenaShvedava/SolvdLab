package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.DAO.IBaseDAO;
import pl.solvd.concerthall.binary.NumOfSeats;

import java.util.List;
import java.util.function.Predicate;

public interface INumOfSeatsDAO extends IBaseDAO<NumOfSeats, Long> {
    NumOfSeats getEntityById(Long id);

    List<NumOfSeats> getAllNumOfSeatsBy(Predicate<NumOfSeats> predicate);
}
