package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.Ticket;

import java.util.List;
import java.util.function.Predicate;

public interface ITicketDAO extends IBaseDAO<Ticket, Long> {
    List<Ticket> getAllTicketBy(Predicate<Ticket> predicate) throws Exception;
    Ticket getEntityById(Long id) throws Exception;
}
