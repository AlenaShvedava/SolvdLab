package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.DAO.impl.OrderDAOImpl;
import pl.solvd.concerthall.DAO.interfacesDAO.IOrderDAO;
import pl.solvd.concerthall.binary.Order;

import java.util.List;
import java.util.function.Predicate;

public class OrderService implements IOrderDAO {
    private final IOrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public Order addEntity(Order order) {
        Order o = new Order();
        o.setCustomerId(order.getCustomerId());
        o.setPosterId(order.getPosterId());
        o.setPriceLevelId(order.getPriceLevelId());
        o.setNumberOfTickets(order.getNumberOfTickets());
        Order createdOrder = this.orderDAO.addEntity(o);
        return order;
    }

    @Override
    public List<Order> getAllOrderBy(Predicate<Order> predicate) {
        return orderDAO.getAllOrderBy(predicate);
    }

    @Override
    public Order getEntityById(Long orderId) {
        return orderDAO.getEntityById(orderId);
    }

    @Override
    public List<Order> updateEntity(Order order) {
        Order o = new Order();
        o.setCustomerId(order.getCustomerId());
        o.setPosterId(order.getPosterId());
        o.setPriceLevelId(order.getPriceLevelId());
        o.setNumberOfTickets(order.getNumberOfTickets());
        o.setId(order.getId());
        return orderDAO.updateEntity(o);
    }

    @Override
    public void deleteEntity(Long id) {
        orderDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Order> getAll() {
        return orderDAO.getAll();
    }
}
