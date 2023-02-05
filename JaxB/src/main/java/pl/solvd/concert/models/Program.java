package pl.solvd.concert.models;

import pl.solvd.concerthall.models.Composition;
import pl.solvd.concerthall.models.ConcertHalls;
import pl.solvd.concerthall.models.Images;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "program")
public class Program {
    private Long id;
    List<pl.solvd.concerthall.models.Images> images;
    List<Composition> composition;
    private String ageLimit;
    private double basePrice;
    List<ConcertHalls> concertHall;
    private String organization;

    public Program() {
    }

    public Program(Long id, List<pl.solvd.concerthall.models.Images> images, List<Composition> composition, String ageLimit, double basePrice, List<ConcertHalls> concertHall, String organization) {
        this.id = id;
        this.images = images;
        this.composition = composition;
        this.ageLimit = ageLimit;
        this.basePrice = basePrice;
        this.concertHall = concertHall;
        this.organization = organization;
    }

    @XmlAttribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "images")
    public List<pl.solvd.concerthall.models.Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    @XmlElement(name = "composition")
    public List<Composition> getComposition() {
        return composition;
    }

    public void setComposition(List<Composition> composition) {
        this.composition = composition;
    }

    @XmlElement(name = "agelimit")
    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    @XmlElement(name = "baseprice")
    public double getBasePrice() {
        return basePrice;
    }


    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    @XmlElement(name = "concerthalls")
    public List<ConcertHalls> getConcertHall() {
        return concertHall;
    }

    public void setConcertHall(List<ConcertHalls> concertHall) {
        this.concertHall = concertHall;
    }

    @XmlElement(name = "organization")
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "\nProgram: \n" +
                "Images: " + images.toString().replace("[", "").replace("]", "").replace(",", "") + "\n" +
                composition.toString().replace("[", "").replace("]", "").replace(",", "") + "\n" +
                "ageLimit: " + ageLimit + "\n" +
                "basePrice: " + basePrice + " euro\n" +
                concertHall.toString().replace("[", "").replace("]", "").replace(",", "") + "\n" +
                "organization: " + organization + "\n";
    }
}
