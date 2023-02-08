package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

import java.util.ArrayList;
import java.util.List;

public class Composition extends MySqlDAO {
    private static Long id;
    private String title;
    private List<Authors> authors;

    public Composition() {
    }

    public Composition(Long id, String title) {
        this.title = title;
        this.id = id;
        authors = new ArrayList<>();
    }

    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Authors> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Composition: \n" +
                "Title: " + title + "\n" +
                "Authors: " + authors + "\n";
    }
}
