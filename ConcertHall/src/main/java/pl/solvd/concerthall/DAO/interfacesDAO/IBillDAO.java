package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.DAO.IBaseDAO;
import pl.solvd.concerthall.binary.Bill;

import java.util.List;
import java.util.function.Predicate;

public interface IBillDAO extends IBaseDAO<Bill, Long> {
    List<Bill> getAllBillBy(Predicate<Bill> predicate);

    Bill getEntityById(Long id);
}
