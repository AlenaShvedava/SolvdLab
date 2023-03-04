package pl.solvd.concerthall.DAO.interfacesDAO;

import pl.solvd.concerthall.DAO.IBaseDAO;
import pl.solvd.concerthall.binary.MySeat;

import java.util.List;
import java.util.function.Predicate;

public interface IMySeatDAO extends IBaseDAO<MySeat, Long> {
    List<MySeat> getAllMySeatBy(Predicate<MySeat> predicate);

    MySeat getEntityById(Long id);
}
