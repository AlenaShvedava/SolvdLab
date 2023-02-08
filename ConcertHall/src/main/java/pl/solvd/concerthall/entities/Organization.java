package pl.solvd.concerthall.entities;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;

public class Organization extends MySqlDAO {
    private static Long id;
    private String name;

    public Organization() {
    }

    public Organization(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Long getId() {
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
        return "\nOrganization: " + name + '\n';
    }
}
