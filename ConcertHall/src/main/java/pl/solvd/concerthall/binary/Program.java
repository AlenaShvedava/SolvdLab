package pl.solvd.concerthall.binary;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "program")
public class Program {
    private static Long id;
    List<Images> images;
    List<Genre> genre;
    List<Composition> composition;
    private String title;
    private String description;
    private String ageLimit;
    private double basePrice;
    List<ConcertHalls> concertHall;
    private Long organizationId;
    List<Poster> poster;

    public Program() {
    }

    public Program(Long id, String title, String description, Long organizationId, String ageLimit, double basePrice) {
        Program.id = id;
        this.title = title;
        this.description = description;
        this.organizationId = organizationId;
        this.ageLimit = ageLimit;
        this.basePrice = basePrice;
    }

    @XmlAttribute
    @JsonProperty("id")
    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        Program.id = id;
    }
    @XmlElement(name = "title")
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "images")
    @JsonProperty("images")
    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    @XmlElement(name = "composition")
    @JsonProperty("composition")
    public List<Composition> getComposition() {
        return composition;
    }

    public void setComposition(List<Composition> composition) {
        this.composition = composition;
    }

    @XmlElement(name = "agelimit")
    @JsonProperty("agelimit")
    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    @XmlElement(name = "baseprice")
    @JsonProperty("baseprice")
    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    @XmlElement(name = "concerthalls")
    @JsonProperty("concerthalls")
    public List<ConcertHalls> getConcertHall() {
        return concertHall;
    }

    public void setConcertHall(List<ConcertHalls> concertHall) {
        this.concertHall = concertHall;
    }

    @XmlElement(name = "organization")
    @JsonProperty("organization")
    public Long getOrganizationId() {
        return organizationId;
    }


    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
    @XmlElement(name = "description")
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @XmlElement(name = "genre")
    @JsonProperty("genre")
    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public List<Poster> getPoster() {
        return poster;
    }

    public void setPoster(List<Poster> poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "\nProgram: \n" +
                "Images: " + images.toString().replace("[", "").replace("]", "").replace(",", "") + "\n" +
                composition.toString().replace("[", "").replace("]", "").replace(",", "") + "\n" +
                "title: " + title + '\'' +
                "description: " + description + '\'' +
                "ageLimit: " + ageLimit + "\n" +
                "basePrice: " + basePrice + " euro\n" +
                concertHall.toString().replace("[", "").replace("]", "").replace(",", "") + "\n" +
                "organization: " + organizationId + "\n";
    }
}
