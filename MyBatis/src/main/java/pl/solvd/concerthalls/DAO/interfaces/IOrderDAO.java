package pl.solvd.concerthalls.DAO.interfaces;

import pl.solvd.concerthalls.binary.Order;
import pl.solvd.concerthalls.DAO.IBaseDAO;

import java.util.List;
import java.util.function.Predicate;

public interface IOrderDAO extends IBaseDAO<Order, Long> {
    List<Order> getAllOrderBy(Predicate<Order> predicate);

    Order getEntityById(Long id);
}
