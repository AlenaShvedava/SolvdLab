package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.DAO.IBaseDAO;
import pl.solvd.concerthall.binary.Ticket;

import java.util.List;
import java.util.function.Predicate;

public interface ITicketDAO extends IBaseDAO<Ticket, Long> {
    List<Ticket> getAllTicketBy(Predicate<Ticket> predicate);

    Ticket getEntityById(Long id);
}
