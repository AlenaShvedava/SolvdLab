package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.EventsHasGenreEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IEventsHasGenreDAO extends IBaseDAO <EventsHasGenreEntity, Long> {
    List<EventsHasGenreEntity> getAllEventsHasGenre() throws Exception;
    List <EventsHasGenreEntity> getAllEventsHasGenreBy (Predicate<EventsHasGenreEntity> predicate) throws Exception;
}
