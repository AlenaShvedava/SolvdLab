package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthalls.DAO.IBaseDAO;
import pl.solvd.concerthalls.binary.Bill;

import java.util.List;
import java.util.function.Predicate;

public interface IBillDAO extends IBaseDAO<Bill, Long> {
    List<Bill> getAllBillBy(Predicate<Bill> predicate);

    Bill getEntityById(Long id);
}
