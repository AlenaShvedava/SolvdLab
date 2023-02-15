package pl.solvd.concerthalls.binary;


import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;
@Alias("Authors")
public class Authors extends Base {
    //private Long id;
    private List<AuthorTypes> authorTypes;
    private String firstName;
    private String lastName;
    //List <Composition> composition;

//    public Authors() {
//    }

    public Authors(Long id, String firstName, String lastName) {
        authorTypes = new ArrayList<>();
        //composition = new ArrayList<>();
        this.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

//    public static Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {id = id;
//    }

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

//    public List<Composition> getComposition() {
//        return composition;
//    }
//
//    public void setComposition(List<Composition> composition) {
//        this.composition = composition;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Authors authors = (Authors) o;
//        return getId() == authors.getId() && Objects.equals(authorTypes, authors.authorTypes) && Objects.equals(firstName, authors.firstName) && Objects.equals(lastName, authors.lastName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), authorTypes, firstName, lastName);
//    }

    @Override
    public String toString() {
        return "\nAuthor Type: " + authorTypes +
                "First name: " + firstName + "\n" +
                "Last name: " + lastName + "\n" +
                "-------------------------------------";
    }
}
