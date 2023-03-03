package pl.solvd.concerthall.binary;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "composition")
public class Composition {
    private static Long id;
    private String title;
    private List<Authors> authors;
    private List<Program> program;

    public Composition() {
    }

    public Composition(Long id, String title) {
        Composition.id = id;
        this.title = title;
    }

    @XmlAttribute
    @JsonProperty("id")
    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        Composition.id = id;
    }

    @XmlElement(name = "title")
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "authors")
    @JsonProperty("authors")
    public List<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Authors> authors) {
        this.authors = authors;
    }

    public List<Program> getProgram() {
        return program;
    }

    public void setProgram(List<Program> program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "Composition: \n" +
                "Title: " + title + "\n" +
                "Authors: " + authors + "\n";
    }
}
