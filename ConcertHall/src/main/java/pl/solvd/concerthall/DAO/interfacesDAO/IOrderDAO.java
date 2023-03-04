package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.DAO.IBaseDAO;
import pl.solvd.concerthall.binary.Order;

import java.util.List;
import java.util.function.Predicate;

public interface IOrderDAO extends IBaseDAO<Order, Long> {
    List<Order> getAllOrderBy(Predicate<Order> predicate);

    Order getEntityById(Long id);
}
