package pl.solvd.concerthalls.services.interfaces;

import pl.solvd.concerthalls.binary.Bill;

import java.util.List;
import java.util.function.Predicate;

public interface IBillService {
    Bill addBill(Bill bill);

    List<Bill> getAllBillsBy(Predicate<Bill> predicate);

    Bill getBillById(Long billId);

    List<Bill> updateBill(Bill bill);

    void deleteBill(Long id);

    List<Bill> getAll();
}
