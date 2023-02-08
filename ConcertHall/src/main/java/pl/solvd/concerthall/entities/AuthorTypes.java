package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

import java.util.ArrayList;
import java.util.List;

public class AuthorTypes extends MySqlDAO {
    private static Long id;
    private String type;
    private List<Authors> authors;

    public AuthorTypes() {
    }

    public AuthorTypes(Long id, String type) {
        authors = new ArrayList<>();
        this.id = id;
        this.type = type;
    }

    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Authors> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return type + '\n';
    }
}
