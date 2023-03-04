package pl.solvd.concerthall.binary;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "authors")
public class Authors {
    @XmlAttribute
    private static Long id;
    private List<AuthorTypes> authorTypes;
    private String firstName;
    private String lastName;
    List<Composition> composition;

    public Authors() {
    }

    public Authors(String firstName, String lastName) {
        //Authors.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    @JsonProperty("id")
    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        Authors.id = id;
    }
    @XmlElement(name = "authortypes")
    @JsonProperty("authortypes")
    public List<AuthorTypes> getAuthorTypes() {
        return authorTypes;
    }

    public void setAuthorTypes(List<AuthorTypes> authorTypes) {
        this.authorTypes = authorTypes;
    }
    @XmlElement(name = "firstname")
    @JsonProperty("firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @XmlElement(name = "lastname")
    @JsonProperty("lastname")
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
