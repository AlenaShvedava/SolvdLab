package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.TicketEntity;

import java.util.List;
import java.util.function.Predicate;

public interface ITicketDAO extends IBaseDAO<TicketEntity, Long> {
    List<TicketEntity> getAllTicket() throws Exception;

    List<TicketEntity> getAllTicketBy(Predicate<TicketEntity> predicate) throws Exception;
}
