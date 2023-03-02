package pl.solvd.concerthalls.services.interfaces;

import pl.solvd.concerthalls.binary.Order;

import java.util.List;
import java.util.function.Predicate;

public interface IOrderService {
    Order addOrder(Order order);

    List<Order> getAllOrderBy(Predicate<Order> predicate);

    Order getOrderById(Long orderId);

    List<Order> updateOrder(Order order);

    void deleteOrder(Long id);

    List<Order> getAll();
}
