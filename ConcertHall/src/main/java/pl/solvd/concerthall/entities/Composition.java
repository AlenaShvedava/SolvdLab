package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

import java.util.List;

public class Composition extends MySqlDAO {
    private int id;
    private String title;

    public Composition() {
    }

    public Composition(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Composition{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
