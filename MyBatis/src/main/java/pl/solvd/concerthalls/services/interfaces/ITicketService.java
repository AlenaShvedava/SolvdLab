package pl.solvd.concerthalls.services.interfaces;

import pl.solvd.concerthalls.binary.Ticket;

import java.util.List;
import java.util.function.Predicate;

public interface ITicketService {
    Ticket addTicket(Ticket ticket);

    List<Ticket> getAllTicketBy(Predicate<Ticket> predicate);

    Ticket getTicketById(Long ticketId);

    List<Ticket> updateTicket(Ticket ticket);

    void deleteTicket(Long id);

    List<Ticket> getAll();
}
