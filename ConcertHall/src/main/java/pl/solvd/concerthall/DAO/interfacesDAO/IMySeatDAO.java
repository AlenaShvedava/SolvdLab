package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthalls.DAO.IBaseDAO;
import pl.solvd.concerthalls.binary.MySeat;

import java.util.List;
import java.util.function.Predicate;

public interface IMySeatDAO extends IBaseDAO<MySeat, Long> {
    List<MySeat> getAllMySeatBy(Predicate<MySeat> predicate);

    MySeat getEntityById(Long id);
}
