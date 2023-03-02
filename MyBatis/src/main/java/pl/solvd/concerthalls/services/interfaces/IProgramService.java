package pl.solvd.concerthalls.services.interfaces;

import pl.solvd.concerthalls.binary.*;

import java.util.List;
import java.util.function.Predicate;

public interface IProgramService {
    Program addProgram(Program program);

    List<Program> getAllProgramsBy(Predicate<Program> predicate);

    List<ConcertHalls> findConcertHallByProgramId(Long programId);

    List<Composition> findCompositionByProgramId(Long programId);

    List<Genre> findGenreByProgramId(Long programId);

    List<Poster> findPostersByProgramId(Long programId);

    Program getProgramById(Long programId);

    List<Program> updateProgram(Program program);

    void deleteProgram(Long id);

    List<Program> getAll();

    List<Images> findImagesByProgramId(Long programId);
}
