package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;

import pl.solvd.concerthall.entities.OrderEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IOrderDAO extends IBaseDAO <OrderEntity, Long> {
    List<OrderEntity> getAllOrder() throws Exception;
    List <OrderEntity> getAllOrderBy (Predicate<OrderEntity> predicate) throws Exception;
}
