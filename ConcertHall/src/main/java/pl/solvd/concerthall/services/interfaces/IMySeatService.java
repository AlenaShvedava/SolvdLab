package pl.solvd.concerthall.services.interfaces;

import pl.solvd.concerthall.binary.MySeat;

import java.util.List;
import java.util.function.Predicate;

public interface IMySeatService {
    MySeat addMySeat(MySeat mySeat);

    List<MySeat> getAllMySeatBy(Predicate<MySeat> predicate);

    MySeat getMySeatById(Long mySeatId);

    List<MySeat> updateMySeat(MySeat mySeat);

    void deleteMySeat(Long id);

    List<MySeat> getAll();
}
