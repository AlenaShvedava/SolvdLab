package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.binary.EventsHasGenre;
import pl.solvd.concerthall.DAO.IBaseDAO;

import java.util.List;
import java.util.function.Predicate;

public interface IEventsHasGenreDAO extends IBaseDAO<EventsHasGenre, Long> {
    List <EventsHasGenre> getAllEventsHasGenreBy (Predicate<EventsHasGenre> predicate) throws Exception;
    EventsHasGenre getEntityById(Long id) throws Exception;
}
