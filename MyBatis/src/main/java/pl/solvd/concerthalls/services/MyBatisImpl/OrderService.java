package pl.solvd.concerthalls.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthalls.DAO.interfaces.IOrderDAO;
import pl.solvd.concerthalls.binary.Order;
import pl.solvd.concerthalls.services.interfaces.IOrderService;
import pl.solvd.concerthalls.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class OrderService implements IOrderService {
    Logger LOG = LogManager.getLogger(OrderService.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();

    @Override
    public Order addOrder(Order order) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IOrderDAO orderDAO = session.getMapper(IOrderDAO.class);
            try {
                orderDAO.addEntity(order);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return order;
    }

    @Override
    public List<Order> getAllOrderBy(Predicate<Order> predicate) {
        return null;
    }

    @Override
    public Order getOrderById(Long orderId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IOrderDAO orderDAO = session.getMapper(IOrderDAO.class);
            Order order = orderDAO.getEntityById(orderId);
            return order;
        }
    }

    @Override
    public List<Order> updateOrder(Order order) {
        List<Order> updatedOrder = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IOrderDAO orderDAO = session.getMapper(IOrderDAO.class);
            try {
                orderDAO.updateEntity(order);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return updatedOrder;
    }

    @Override
    public void deleteOrder(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IOrderDAO orderDAO = session.getMapper(IOrderDAO.class);
            try {
                orderDAO.deleteEntity(id);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Order> getAll() {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IOrderDAO orderDAO = session.getMapper(IOrderDAO.class);
            List<Order> list = orderDAO.getAll();
            return list;
        }
    }
}
