package pl.solvd.concert.models;

import pl.solvd.concerthall.models.Authors;
import pl.solvd.concerthall.models.Genre;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "composition")
public class Composition {
    private Long id;
    private List <pl.solvd.concerthall.models.Genre> genre;
    private String title;
    private String description;
    private List<pl.solvd.concerthall.models.Authors> authors;

    public Composition() {
    }

    public Composition(Long id, List <pl.solvd.concerthall.models.Genre> genre, String title, String description, List<pl.solvd.concerthall.models.Authors> authors) {
        this.id = id;
        this.genre = genre;
        this.title = title;
        this.description = description;
        this.authors = authors;
    }

    @XmlAttribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "genre")
    public List <pl.solvd.concerthall.models.Genre> getGenre() {
        return genre;
    }

    public void setGenre(List <Genre> genre) {
        this.genre = genre;
    }

    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "authors")
    public List<pl.solvd.concerthall.models.Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Authors> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Composition: \n" +
                genre +
                "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Authors: " + authors + "\n";
    }
}
