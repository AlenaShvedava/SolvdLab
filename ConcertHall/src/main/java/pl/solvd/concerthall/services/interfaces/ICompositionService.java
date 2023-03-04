package pl.solvd.concerthall.services.interfaces;

import pl.solvd.concerthall.binary.Authors;
import pl.solvd.concerthall.binary.Composition;
import pl.solvd.concerthall.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public interface ICompositionService {
    Composition addComposition(Composition composition);

    List<Composition> getAllCompositionsBy(Predicate<Composition> predicate);

    List<Authors> findAuthorsByCompositionId(Long compositionId);

    List<Program> findProgramsByCompositionId(Long compositionId);

    Composition getCompositionById(Long compositionId);

    List<Composition> updateComposition(Composition composition);

    void deleteComposition(Long id);

    List<Composition> getAll();
}
