package pl.solvd.concerthalls.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthalls.DAO.interfaces.ITicketDAO;
import pl.solvd.concerthalls.binary.Ticket;
import pl.solvd.concerthalls.services.interfaces.ITicketService;
import pl.solvd.concerthalls.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TicketService implements ITicketService {
    Logger LOG = LogManager.getLogger(TicketService.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();

    @Override
    public Ticket addTicket(Ticket ticket) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            ITicketDAO ticketDAO = session.getMapper(ITicketDAO.class);
            try {
                ticketDAO.addEntity(ticket);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return ticket;
    }

    @Override
    public List<Ticket> getAllTicketBy(Predicate<Ticket> predicate) {
        return null;
    }

    @Override
    public Ticket getTicketById(Long ticketId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            ITicketDAO ticketDAO = session.getMapper(ITicketDAO.class);
            Ticket ticket = ticketDAO.getEntityById(ticketId);
            return ticket;
        }
    }

    @Override
    public List<Ticket> updateTicket(Ticket ticket) {
        List<Ticket> updatedTicket = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            ITicketDAO ticketDAO = session.getMapper(ITicketDAO.class);
            try {
                ticketDAO.updateEntity(ticket);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return updatedTicket;
    }

    @Override
    public void deleteTicket(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            ITicketDAO ticketDAO = session.getMapper(ITicketDAO.class);
            try {
                ticketDAO.deleteEntity(id);
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
    public List<Ticket> getAll() {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            ITicketDAO ticketDAO = session.getMapper(ITicketDAO.class);
            List<Ticket> list = ticketDAO.getAll();
            return list;
        }
    }
}
