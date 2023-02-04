package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;

import pl.solvd.concerthall.entities.BillEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IBillDAO extends IBaseDAO <BillEntity, Long>{
    List<BillEntity> getAllBill() throws Exception;
    List <BillEntity> getAllBillBy (Predicate<BillEntity> predicate) throws Exception;
}
