package pl.solvd.concerthall.services;

import pl.solvd.concerthall.dao.impl.TicketDAOImpl;
import pl.solvd.concerthall.dao.interfacesDAO.ITicketDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Ticket;

import java.util.List;
import java.util.function.Predicate;

public class TicketService extends MySqlDAO implements ITicketDAO {
    private final ITicketDAO ticketDAO = new TicketDAOImpl();

    @Override
    public Ticket addEntity(Ticket ticket) throws Exception {
        Ticket t = new Ticket();
        t.setOrderId(ticket.getOrderId());
        t.setMySeatId(ticket.getMySeatId());
        Ticket createdTicket = this.ticketDAO.addEntity(t);
        return ticket;
    }

    @Override
    public List<Ticket> getAllTicketBy(Predicate<Ticket> predicate) throws Exception {
        return ticketDAO.getAllTicketBy(predicate);
    }

    @Override
    public Ticket getEntityById(Long ticketId) throws Exception {
        return ticketDAO.getEntityById(ticketId);
    }

    @Override
    public List<Ticket> updateEntity(Ticket ticket) throws Exception {
        Ticket t = new Ticket();
        t.setOrderId(ticket.getOrderId());
        t.setMySeatId(ticket.getMySeatId());
        t.setId(Ticket.getId());
        return ticketDAO.updateEntity(t);
    }

    @Override
    public void deleteEntity(Long id) throws Exception {
        ticketDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Ticket> getAll() throws Exception {
        return ticketDAO.getAll();
    }
}
