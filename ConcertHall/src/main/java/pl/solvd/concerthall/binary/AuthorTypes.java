package pl.solvd.concerthall.binary;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "authortypes")
public class AuthorTypes {
    @XmlAttribute
    private static Long id;
    private String type;
    private List<Authors> authors;

    public AuthorTypes() {
    }

    public AuthorTypes(Long id, String type) {
        AuthorTypes.id = id;
        this.type = type;
    }

    @JsonProperty("id")
    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        AuthorTypes.id = id;
    }
    @XmlElement(name = "type")
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Authors> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return type + '\n';
    }
}
