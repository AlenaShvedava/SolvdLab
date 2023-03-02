package pl.solvd.concerthalls.binary;

import java.util.List;

public class Authors {
    private Long id;
    private List<AuthorTypes> authorTypes;
    private String firstName;
    private String lastName;
    List<Composition> composition;

    public Authors() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
