package pl.solvd.concerthall.services.interfaces;

import pl.solvd.concerthall.binary.Events;
import pl.solvd.concerthall.binary.Genre;

import java.util.List;
import java.util.function.Predicate;

public interface IEventsService {
    Events addEvent(Events event);

    List<Events> getAllEventsBy(Predicate<Events> predicate);

    List<Genre> findGenresByEventId(Long eventId);

    Events getEventById(Long eventId);

    List<Events> updateEvent(Events event);

    void deleteEvent(Long id);

    List<Events> getAll();
}
