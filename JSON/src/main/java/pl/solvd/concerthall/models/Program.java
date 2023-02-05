package pl.solvd.concerthall.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Program {
    private Long id;
    private String title;
    private List<Images> images;
    private List<Composition> composition;
    private String ageLimit;
    private double basePrice;
    private List<ConcertHalls> concertHall;
    private String organization;

    public Program() {
    }

    public Program(Long id, String title, List<Images> images, List<Composition> composition, String ageLimit, double basePrice, List<ConcertHalls> concertHall, String organization) {
        this.id = id;
        this.title = title;
        this.images = images;
        this.composition = composition;
        this.ageLimit = ageLimit;
        this.basePrice = basePrice;
        this.concertHall = concertHall;
        this.organization = organization;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("images")
    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    @JsonProperty("composition")
    public List<Composition> getComposition() {
        return composition;
    }

    public void setComposition(List<Composition> composition) {
        this.composition = composition;
    }

    @JsonProperty("agelimit")
    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    @JsonProperty("baseprice")
    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    @JsonProperty("concerthalls")
    public List<ConcertHalls> getConcertHall() {
        return concertHall;
    }

    public void setConcertHall(List<ConcertHalls> concertHall) {
        this.concertHall = concertHall;
    }

    @JsonProperty("organization")
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
