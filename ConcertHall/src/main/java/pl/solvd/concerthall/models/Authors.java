package pl.solvd.concerthall.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "authors")
public class Authors {
    private Long id;
    private List <AuthorTypes> authorTypes;
    private String firstName;
    private String lastName;

    public Authors() {
    }

    public Authors(Long id, List <AuthorTypes> authorTypes, String firstName, String lastName) {
        this.id = id;
        this.authorTypes = authorTypes;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @XmlAttribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement (name = "authortypes")
    public List<AuthorTypes> getAuthorTypes() {
        return authorTypes;
    }

    public void setAuthorTypes(List<AuthorTypes> authorTypes) {
        this.authorTypes = authorTypes;
    }

    @XmlElement(name = "firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "lastname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return  "\nAuthor Type: " + authorTypes +
                "First name: " + firstName + "\n" +
                "Last name: " + lastName + "\n" +
                "-------------------------------------";
    }
}
