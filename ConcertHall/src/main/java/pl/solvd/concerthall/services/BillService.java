package pl.solvd.concerthall.services;

import pl.solvd.concerthall.entities.Authors;
import pl.solvd.concerthall.exceptions.ServiceException;
import pl.solvd.concerthall.models.Bill;

public interface BillService {
    Bill saveEntity (Bill bill) throws Exception;
    Bill getEntityById (Long id) throws Exception;
    void updateEntity (Bill bill) throws ServiceException;
    String deleteEntity (Long id) throws ServiceException;
}
