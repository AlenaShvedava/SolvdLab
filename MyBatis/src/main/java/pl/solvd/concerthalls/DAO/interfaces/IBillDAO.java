package pl.solvd.concerthalls.DAO.interfaces;

import pl.solvd.concerthalls.binary.Bill;
import pl.solvd.concerthalls.DAO.IBaseDAO;

import java.util.List;
import java.util.function.Predicate;

public interface IBillDAO extends IBaseDAO<Bill, Long> {
    List<Bill> getAllBillBy(Predicate<Bill> predicate);

    Bill getEntityById(Long id);
}
