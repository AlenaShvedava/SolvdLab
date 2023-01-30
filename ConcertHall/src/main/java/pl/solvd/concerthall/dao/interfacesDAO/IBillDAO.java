package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;

import pl.solvd.concerthall.entities.Bill;

import java.util.List;
import java.util.function.Predicate;

public interface IBillDAO extends IBaseDAO <Bill, Long>{
    List<Bill> getAllBill() throws Exception;
    List <Bill> getAllBillBy (Predicate<Bill> predicate) throws Exception;
}
