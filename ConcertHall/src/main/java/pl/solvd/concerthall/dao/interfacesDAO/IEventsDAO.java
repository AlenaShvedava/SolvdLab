package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.DAO.IBaseDAO;
import pl.solvd.concerthall.binary.Events;
import pl.solvd.concerthall.binary.Genre;

import java.util.List;
import java.util.function.Predicate;

public interface IEventsDAO extends IBaseDAO<Events, Long> {
    Events getEntityById(Long id);

    List<Events> getAllEventsBy(Predicate<Events> predicate);

    List<Genre> findGenresByEventId(Long eventId);
}
