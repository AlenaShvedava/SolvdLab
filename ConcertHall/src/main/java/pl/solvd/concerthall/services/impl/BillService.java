package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.DAO.impl.BillDAOImpl;
import pl.solvd.concerthall.DAO.interfacesDAO.IBillDAO;
import pl.solvd.concerthall.binary.Bill;

import java.util.List;
import java.util.function.Predicate;

public class BillService implements IBillDAO {
    private final IBillDAO billDAO = new BillDAOImpl();

    @Override
    public Bill addEntity(Bill bill) {
        Bill b = new Bill();
        b.setOrderId(bill.getOrderId());
        Bill createdBill = this.billDAO.addEntity(b);
        return bill;
    }

    @Override
    public List<Bill> getAllBillBy(Predicate<Bill> predicate) {
        return billDAO.getAllBillBy(predicate);
    }

    @Override
    public Bill getEntityById(Long billId) {
        return billDAO.getEntityById(billId);
    }

    @Override
    public List<Bill> updateEntity(Bill bill) {
        Bill b = new Bill();
        b.setOrderId(bill.getOrderId());
        b.setId(Bill.getId());
        return billDAO.updateEntity(b);
    }

    @Override
    public void deleteEntity(Long id) {
        billDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Bill> getAll() {
        return billDAO.getAll();
    }
}
