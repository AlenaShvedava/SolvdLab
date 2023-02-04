package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.ConcertHallsEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IConcertHallsDAO extends IBaseDAO <ConcertHallsEntity, Long> {
    List  <ConcertHallsEntity> getAllConcertHalls() throws Exception;
    List <ConcertHallsEntity> getAllConcertHallsBy (Predicate<ConcertHallsEntity> predicate) throws Exception;

}
