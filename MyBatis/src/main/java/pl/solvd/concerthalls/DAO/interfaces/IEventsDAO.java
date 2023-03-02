package pl.solvd.concerthalls.DAO.interfaces;

import pl.solvd.concerthalls.binary.Events;
import pl.solvd.concerthalls.DAO.IBaseDAO;
import pl.solvd.concerthalls.binary.Genre;

import java.util.List;
import java.util.function.Predicate;

public interface IEventsDAO extends IBaseDAO<Events, Long> {
    Events getEntityById(Long id);

    List<Events> getAllEventsBy(Predicate<Events> predicate);

    List<Genre> findGenresByEventId(Long eventId);
}
