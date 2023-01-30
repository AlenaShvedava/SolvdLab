package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.ConcertHalls;

import java.util.List;
import java.util.function.Predicate;

public interface IConcertHallsDAO extends IBaseDAO <ConcertHalls, Long> {
    List  <ConcertHalls> getAllConcertHalls() throws Exception;
    List <ConcertHalls> getAllConcertHallsBy (Predicate<ConcertHalls> predicate) throws Exception;

}
