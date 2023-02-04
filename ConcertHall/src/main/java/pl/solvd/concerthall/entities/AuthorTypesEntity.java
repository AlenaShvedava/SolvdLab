package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

public class AuthorTypesEntity extends MySqlDAO {
    private Long id;
    private String type;

    public AuthorTypesEntity() {
    }

    public AuthorTypesEntity(Long id, String type) {
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
        return "AuthorTypes{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
