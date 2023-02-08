package pl.solvd.concerthall.services;

import pl.solvd.concerthall.dao.impl.OrderDAOImpl;
import pl.solvd.concerthall.dao.interfacesDAO.IOrderDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Order;

import java.util.List;
import java.util.function.Predicate;

public class OrderService extends MySqlDAO implements IOrderDAO {
    private final IOrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public Order addEntity(Order order) throws Exception {
        Order o = new Order();
        o.setCustomerId(order.getCustomerId());
        o.setPosterId(order.getPosterId());
        o.setPriceLevelId(order.getPriceLevelId());
        o.setNumberOfTickets(order.getNumberOfTickets());
        Order createdOrder = this.orderDAO.addEntity(o);
        return order;
    }

    @Override
    public List<Order> getAllOrderBy(Predicate<Order> predicate) throws Exception {
        return orderDAO.getAllOrderBy(predicate);
    }

    @Override
    public Order getEntityById(Long orderId) throws Exception {
        return orderDAO.getEntityById(orderId);
    }

    @Override
    public List<Order> updateEntity(Order order) throws Exception {
        Order o = new Order();
        o.setCustomerId(order.getCustomerId());
        o.setPosterId(order.getPosterId());
        o.setPriceLevelId(order.getPriceLevelId());
        o.setNumberOfTickets(order.getNumberOfTickets());
        o.setId(Order.getId());
        return orderDAO.updateEntity(o);
    }

    @Override
    public void deleteEntity(Long id) throws Exception {
        orderDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Order> getAll() throws Exception {
        return orderDAO.getAll();
    }
}
