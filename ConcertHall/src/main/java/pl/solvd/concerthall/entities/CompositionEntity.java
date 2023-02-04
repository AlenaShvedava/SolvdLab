package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

public class CompositionEntity extends MySqlDAO {
    private Long id;
    private String title;

    public CompositionEntity() {
    }

    public CompositionEntity(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
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

    @Override
    public String toString() {
        return "Composition{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
