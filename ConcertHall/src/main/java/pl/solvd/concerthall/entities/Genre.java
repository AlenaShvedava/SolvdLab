package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

import java.util.List;

public class Genre extends MySqlDAO {
    private Long id;
    private String type;

    public Genre() {
    }

    public Genre(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
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

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
