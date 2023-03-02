package pl.solvd.concerthalls.services.interfaces;

import pl.solvd.concerthalls.binary.NumOfSeats;

import java.util.List;
import java.util.function.Predicate;

public interface INumOfSeatsService {
    NumOfSeats addNumOfSeats(NumOfSeats numOfSeats);

    List<NumOfSeats> getAllNumOfSeatsBy(Predicate<NumOfSeats> predicate);

    NumOfSeats getNumOfSeatsById(Long numOfSeatsId);

    List<NumOfSeats> updateNumOfSeats(NumOfSeats numOfSeats);

    void deleteNumOfSeats(Long id);

    List<NumOfSeats> getAll();
}
