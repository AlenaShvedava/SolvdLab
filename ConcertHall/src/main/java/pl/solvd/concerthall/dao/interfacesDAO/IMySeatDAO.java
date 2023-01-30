package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.MySeat;

import java.util.List;
import java.util.function.Predicate;

public interface IMySeatDAO extends IBaseDAO <MySeat, Long> {
    List<MySeat> getAllMySeat() throws Exception;
    List <MySeat> getAllMySeatBy (Predicate<MySeat> predicate) throws Exception;
}
