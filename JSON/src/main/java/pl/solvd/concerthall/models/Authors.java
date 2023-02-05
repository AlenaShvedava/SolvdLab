package pl.solvd.concerthall.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Authors {
    private Long id;
    private List<AuthorTypes> authorTypes;
    private String firstName;
    private String lastName;

    public Authors() {
    }

    public Authors(Long id, List<AuthorTypes> authorTypes, String firstName, String lastName) {
        this.id = id;
        this.authorTypes = authorTypes;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("authortypes")
    public List<AuthorTypes> getAuthorTypes() {
        return authorTypes;
    }

    public void setAuthorTypes(List<AuthorTypes> authorTypes) {
        this.authorTypes = authorTypes;
    }

    @JsonProperty("firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "\nAuthor Type: " + authorTypes +
                "First name: " + firstName + "\n" +
                "Last name: " + lastName + "\n" +
                "-------------------------------------";
    }
}
