package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.EventsHasGenre;

import java.util.List;
import java.util.function.Predicate;

public interface IEventsHasGenreDAO extends IBaseDAO <EventsHasGenre, Long> {
    List <EventsHasGenre> getAllEventsHasGenreBy (Predicate<EventsHasGenre> predicate) throws Exception;
    EventsHasGenre getEntityById(Long id) throws Exception;
}
