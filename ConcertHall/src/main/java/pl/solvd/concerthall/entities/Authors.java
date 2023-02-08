package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

import java.util.ArrayList;
import java.util.List;

public class Authors extends MySqlDAO {
    private static Long id;
    private List<AuthorTypes> authorTypes;
    private String firstName;
    private String lastName;
    List <Composition> composition;

    public Authors() {
    }

    public Authors(Long id, String firstName, String lastName) {
        authorTypes = new ArrayList<>();
        composition = new ArrayList<>();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        Authors.id = id;
    }

    public List<AuthorTypes> getAuthorTypes() {
        return authorTypes;
    }

    public void setAuthorTypes(List<AuthorTypes> authorTypes) {
        this.authorTypes = authorTypes;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Composition> getComposition() {
        return composition;
    }

    public void setComposition(List<Composition> composition) {
        this.composition = composition;
    }

    @Override
    public String toString() {
        return "\nAuthor Type: " + authorTypes +
                "First name: " + firstName + "\n" +
                "Last name: " + lastName + "\n" +
                "-------------------------------------";
    }
}
