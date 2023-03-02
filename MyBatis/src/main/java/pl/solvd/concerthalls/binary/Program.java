package pl.solvd.concerthalls.binary;

import java.util.List;

public class Program {
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public List<Composition> getComposition() {
        return composition;
    }

    public void setComposition(List<Composition> composition) {
        this.composition = composition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public List<ConcertHalls> getConcertHall() {
        return concertHall;
    }

    public void setConcertHall(List<ConcertHalls> concertHall) {
        this.concertHall = concertHall;
    }

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
