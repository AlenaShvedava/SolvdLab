package pl.solvd.concerthall.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.solvd.concerthall.models.Poster;

import java.util.ArrayList;
import java.util.List;

public class ConcertHall {
    private List <Poster> posters = new ArrayList<>();

    public ConcertHall() {
    }

    public ConcertHall(List<Poster> posters) {
        this.posters = posters;
    }

    @JsonProperty ("poster")
    public List <Poster> getPoster() {
        return posters;
    }

    public void setPoster(List <Poster> posters) {
        this.posters = posters;
    }

    @Override
    public String toString() {
        return "Posters: " + posters + "\n" +
        "=====================================";
    }
}
