package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthalls.DAO.IBaseDAO;
import pl.solvd.concerthalls.binary.Order;

import java.util.List;
import java.util.function.Predicate;

public interface IOrderDAO extends IBaseDAO<Order, Long> {
    List<Order> getAllOrderBy(Predicate<Order> predicate);

    Order getEntityById(Long id);
}
