package pl.solvd.concerthalls.DAO.interfaces;

import pl.solvd.concerthalls.binary.MySeat;
import pl.solvd.concerthalls.DAO.IBaseDAO;

import java.util.List;
import java.util.function.Predicate;

public interface IMySeatDAO extends IBaseDAO<MySeat, Long> {
    List<MySeat> getAllMySeatBy(Predicate<MySeat> predicate);

    MySeat getEntityById(Long id);
}
