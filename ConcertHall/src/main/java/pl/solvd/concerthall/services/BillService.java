package pl.solvd.concerthall.services;

import pl.solvd.concerthall.dao.impl.BillDAOImpl;
import pl.solvd.concerthall.dao.interfacesDAO.IBillDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Bill;

import java.util.List;
import java.util.function.Predicate;

public class BillService extends MySqlDAO implements IBillDAO {
    private final IBillDAO billDAO = new BillDAOImpl();

    @Override
    public Bill addEntity(Bill bill) throws Exception {
        Bill b = new Bill();
        b.setOrderId(bill.getOrderId());
        Bill createdBill = this.billDAO.addEntity(b);
        return bill;
    }

    @Override
    public List<Bill> getAllBillBy(Predicate<Bill> predicate) throws Exception {
        return billDAO.getAllBillBy(predicate);
    }

    @Override
    public Bill getEntityById(Long billId) throws Exception {
        return billDAO.getEntityById(billId);
    }

    @Override
    public List<Bill> updateEntity(Bill bill) throws Exception {
        Bill b = new Bill();
        b.setOrderId(bill.getOrderId());
        b.setId(Bill.getId());
        return billDAO.updateEntity(b);
    }

    @Override
    public void deleteEntity(Long id) throws Exception {
        billDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<Bill> getAll() throws Exception {
        return billDAO.getAll();
    }
}
