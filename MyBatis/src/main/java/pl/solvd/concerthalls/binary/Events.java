package pl.solvd.concerthalls.binary;

import java.util.List;

public class Events {
    private Long id;
    private String category;
    List<Genre> genre;

    public Events() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Events{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }
}
