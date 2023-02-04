package pl.solvd.concerthall.dao.interfacesDAO;

import pl.solvd.concerthall.dao.IBaseDAO;
import pl.solvd.concerthall.entities.MySeatEntity;

import java.util.List;
import java.util.function.Predicate;

public interface IMySeatDAO extends IBaseDAO <MySeatEntity, Long> {
    List<MySeatEntity> getAllMySeat() throws Exception;
    List <MySeatEntity> getAllMySeatBy (Predicate<MySeatEntity> predicate) throws Exception;
}
