package pl.solvd.concerthall.services;

import pl.solvd.concerthall.dao.impl.MySeatDAOImpl;
import pl.solvd.concerthall.dao.interfacesDAO.IMySeatDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.MySeat;

import java.util.List;
import java.util.function.Predicate;

public class MySeatService extends MySqlDAO implements IMySeatDAO {
    private final IMySeatDAO mySeatDAO = new MySeatDAOImpl();

    @Override
    public MySeat addEntity(MySeat mySeat) throws Exception {
        MySeat ms = new MySeat();
        ms.setRowNumber(mySeat.getRowNumber());
        ms.setSeatNumber(mySeat.getSeatNumber());
        ms.setNumOfSeatsId(mySeat.getNumOfSeatsId());
        MySeat createdMySeat = this.mySeatDAO.addEntity(ms);
        return mySeat;
    }

    @Override
    public List<MySeat> getAllMySeatBy(Predicate<MySeat> predicate) throws Exception {
        return mySeatDAO.getAllMySeatBy(predicate);
    }

    @Override
    public MySeat getEntityById(Long mySeatId) throws Exception {
        return mySeatDAO.getEntityById(mySeatId);
    }

    @Override
    public List<MySeat> updateEntity(MySeat mySeat) throws Exception {
        MySeat ms = new MySeat();
        ms.setRowNumber(mySeat.getRowNumber());
        ms.setSeatNumber(mySeat.getSeatNumber());
        ms.setNumOfSeatsId(mySeat.getNumOfSeatsId());
        ms.setId(MySeat.getId());
        return mySeatDAO.updateEntity(ms);
    }

    @Override
    public void deleteEntity(Long id) throws Exception {
        mySeatDAO.deleteEntity(id);
        System.out.println("Removal completed");
    }

    @Override
    public List<MySeat> getAll() throws Exception {
        return mySeatDAO.getAll();
    }
}
