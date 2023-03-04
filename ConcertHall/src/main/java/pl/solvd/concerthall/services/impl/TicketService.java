package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.DAO.impl.TicketDAOImpl;
import pl.solvd.concerthall.DAO.interfacesDAO.ITicketDAO;
import pl.solvd.concerthall.binary.Ticket;

import java.util.List;
import java.util.function.Predicate;

public class TicketService implements ITicketDAO {
    private final ITicketDAO ticketDAO = new TicketDAOImpl();

    @Override
    public Ticket addEntity(Ticket ticket) {
        Ticket t = new Ticket();
        t.setOrderId(ticket.getOrderId());
        t.setMySeatId(ticket.getMySeatId());
        Ticket createdTicket = this.ticketDAO.addEntity(t);
        return ticket;
    }

    @Override
    public List<Ticket> getAllTicketBy(Predicate<Ticket> predicate) {
        return ticketDAO.getAllTicketBy(predicate);
    }

    @Override
    public Ticket getEntityById(Long ticketId) {
        return ticketDAO.getEntityById(ticketId);
    }

    @Override
    public List<Ticket> updateEntity(Ticket ticket) {
        Ticket t = new Ticket();
        t.setOrderId(ticket.getOrderId());
        t.setMySeatId(ticket.getMySeatId());
        t.setId(ticket.getId());
        return ticketDAO.updateEntity(t);
    }

    @Override
    public void deleteEntity(Long id) {
        ticketDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Ticket> getAll() {
        return ticketDAO.getAll();
    }
}
