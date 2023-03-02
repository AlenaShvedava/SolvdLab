package pl.solvd.concerthalls.DAO.interfaces;

import pl.solvd.concerthalls.binary.NumOfSeats;
import pl.solvd.concerthalls.DAO.IBaseDAO;

import java.util.List;
import java.util.function.Predicate;

public interface INumOfSeatsDAO extends IBaseDAO<NumOfSeats, Long> {
    NumOfSeats getEntityById(Long id);

    List<NumOfSeats> getAllNumOfSeatsBy(Predicate<NumOfSeats> predicate);
}
