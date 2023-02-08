package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.NumOfSeats;

import java.util.List;
import java.util.function.Predicate;

public interface INumOfSeatsDAO extends IBaseDAO <NumOfSeats, Long> {
    NumOfSeats getEntityById(Long id) throws Exception;
    List <NumOfSeats> getAllNumOfSeatsBy (Predicate<NumOfSeats> predicate) throws Exception;
}
