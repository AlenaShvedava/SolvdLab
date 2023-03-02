package pl.solvd.concerthalls.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthalls.DAO.interfaces.IEventsDAO;
import pl.solvd.concerthalls.binary.Events;
import pl.solvd.concerthalls.binary.Genre;
import pl.solvd.concerthalls.services.interfaces.IEventsService;
import pl.solvd.concerthalls.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class EventsService implements IEventsService {
    Logger LOG = LogManager.getLogger(EventsService.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();

    @Override
    public Events addEvent(Events event) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IEventsDAO eventDAO = session.getMapper(IEventsDAO.class);
            try {
                eventDAO.addEntity(event);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return event;
    }

    @Override
    public List<Events> getAllEventsBy(Predicate<Events> predicate) {
        return null;
    }

    @Override
    public Events getEventById(Long eventId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IEventsDAO eventDAO = session.getMapper(IEventsDAO.class);
            Events event = eventDAO.getEntityById(eventId);
            return event;
        }
    }

    @Override
    public List<Events> updateEvent(Events event) {
        List<Events> updatedEvent = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IEventsDAO eventDAO = session.getMapper(IEventsDAO.class);
            try {
                eventDAO.updateEntity(event);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return updatedEvent;
    }

    @Override
    public void deleteEvent(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IEventsDAO eventDAO = session.getMapper(IEventsDAO.class);
            try {
                eventDAO.deleteEntity(id);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Events> getAll() {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IEventsDAO eventDAO = session.getMapper(IEventsDAO.class);
            List<Events> list = eventDAO.getAll();
            return list;
        }
    }

    @Override
    public List<Genre> findGenresByEventId(Long eventId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IEventsDAO eventDAO = session.getMapper(IEventsDAO.class);
            List<Genre> genresList = eventDAO.findGenresByEventId(eventId);
            return genresList;
        }
    }
}
