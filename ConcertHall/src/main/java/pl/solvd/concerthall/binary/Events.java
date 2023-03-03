package pl.solvd.concerthall.binary;

import java.util.List;

public class Events {
    private static Long id;
    private String category;
    List<Genre> genre;

    public Events() {
    }

    public Events(Long id, String category) {
        Events.id = id;
        this.category = category;
    }

    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        Events.id = id;
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
