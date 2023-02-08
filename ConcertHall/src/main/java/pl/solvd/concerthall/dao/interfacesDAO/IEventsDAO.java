package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.Events;

import java.util.List;
import java.util.function.Predicate;

public interface IEventsDAO extends IBaseDAO <Events, Long> {
    Events getEntityById(Long id) throws Exception;
    List <Events> getAllEventsBy (Predicate<Events> predicate) throws Exception;
}
