package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.NumOfSeatsEntity;

import java.util.List;
import java.util.function.Predicate;

public interface INumOfSeatsDAO extends IBaseDAO <NumOfSeatsEntity, Long> {
    List<NumOfSeatsEntity> getAllNumOfSeats() throws Exception;
    List <NumOfSeatsEntity> getAllNumOfSeatsBy (Predicate<NumOfSeatsEntity> predicate) throws Exception;
}
