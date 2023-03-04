package pl.solvd.concerthall.services.interfaces;

import pl.solvd.concerthall.binary.Poster;
import pl.solvd.concerthall.binary.Program;

import java.util.List;
import java.util.function.Predicate;

public interface IPosterService {
    Poster addPoster(Poster poster);

    List<Poster> getAllPostersBy(Predicate<Poster> predicate);

    List<Program> findProgramsByPosterId(Long postersId);

    Poster getPosterById(Long postersId);

    List<Poster> updatePoster(Poster poster);

    void deletePoster(Long id);

    List<Poster> getAll();
}
