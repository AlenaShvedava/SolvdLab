package pl.solvd.concerthall.services.impl;

import pl.solvd.concerthall.DAO.impl.MySeatDAOImpl;
import pl.solvd.concerthall.DAO.interfacesDAO.IMySeatDAO;
import pl.solvd.concerthall.binary.MySeat;

import java.util.List;
import java.util.function.Predicate;

public class MySeatService implements IMySeatDAO {
    private final IMySeatDAO mySeatDAO = new MySeatDAOImpl();

    @Override
    public MySeat addEntity(MySeat mySeat) {
        MySeat ms = new MySeat();
        ms.setRowNumber(mySeat.getRowNumber());
        ms.setSeatNumber(mySeat.getSeatNumber());
        ms.setNumOfSeatsId(mySeat.getNumOfSeatsId());
        MySeat createdMySeat = this.mySeatDAO.addEntity(ms);
        return mySeat;
    }

    @Override
    public List<MySeat> getAllMySeatBy(Predicate<MySeat> predicate) {
        return mySeatDAO.getAllMySeatBy(predicate);
    }

    @Override
    public MySeat getEntityById(Long mySeatId) {
        return mySeatDAO.getEntityById(mySeatId);
    }

    @Override
    public List<MySeat> updateEntity(MySeat mySeat) {
        MySeat ms = new MySeat();
        ms.setRowNumber(mySeat.getRowNumber());
        ms.setSeatNumber(mySeat.getSeatNumber());
        ms.setNumOfSeatsId(mySeat.getNumOfSeatsId());
        ms.setId(mySeat.getId());
        return mySeatDAO.updateEntity(ms);
    }

    @Override
    public void deleteEntity(Long id) {
        mySeatDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<MySeat> getAll() {
        return mySeatDAO.getAll();
    }
}
