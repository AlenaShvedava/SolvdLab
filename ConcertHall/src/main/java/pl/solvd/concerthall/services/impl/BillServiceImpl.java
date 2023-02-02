package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.exceptions.ServiceException;
import pl.solvd.concerthall.models.Bill;
import pl.solvd.concerthall.services.BillService;

public class BillServiceImpl extends MySqlDAO implements BillService {
    @Override
    public Bill saveEntity(Bill bill) throws Exception {
        return null;
    }

    @Override
    public Bill getEntityById(Long id) throws Exception {
        return null;
    }

    @Override
    public void updateEntity(Bill bill) {

    }

    @Override
    public String deleteEntity(Long id) throws ServiceException {
        return null;
    }
}
