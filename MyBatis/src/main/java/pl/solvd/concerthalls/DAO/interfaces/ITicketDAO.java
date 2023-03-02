package pl.solvd.concerthalls.DAO.interfaces;

import pl.solvd.concerthalls.binary.Ticket;
import pl.solvd.concerthalls.DAO.IBaseDAO;

import java.util.List;
import java.util.function.Predicate;

public interface ITicketDAO extends IBaseDAO<Ticket, Long> {
    List<Ticket> getAllTicketBy(Predicate<Ticket> predicate);

    Ticket getEntityById(Long id);
}
