package pl.solvd.concerthall.entities;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;

public class OrganizationEntity extends MySqlDAO {
    private Long id;
    private String name;

    public OrganizationEntity() {
    }

    public OrganizationEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
