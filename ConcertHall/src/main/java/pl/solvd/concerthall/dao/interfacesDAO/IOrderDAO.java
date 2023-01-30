package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;

import pl.solvd.concerthall.entities.Order;

import java.util.List;
import java.util.function.Predicate;

public interface IOrderDAO extends IBaseDAO <Order, Long> {
    List<Order> getAllOrder() throws Exception;
    List <Order> getAllOrderBy (Predicate<Order> predicate) throws Exception;
}
