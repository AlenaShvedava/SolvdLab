package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.EventsEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IEventsDAO extends IBaseDAO <EventsEntity, Long> {
    List<EventsEntity> getAllEvents() throws Exception;
    List <EventsEntity> getAllEventsBy (Predicate<EventsEntity> predicate) throws Exception;
}
