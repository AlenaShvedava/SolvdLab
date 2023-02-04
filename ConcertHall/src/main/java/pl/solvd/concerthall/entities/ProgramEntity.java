package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

public class ProgramEntity extends MySqlDAO {
    private Long id;
    private String title;
    private String description;
    private Long organizationId;
    private String ageLimit;
    private double basePrice;

    public ProgramEntity() {
    }

    public ProgramEntity(Long id, String title, String description, Long organizationId, String ageLimit, double basePrice) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.organizationId = organizationId;
        this.ageLimit = ageLimit;
        this.basePrice = basePrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", organizationId=" + organizationId +
                ", ageLimit='" + ageLimit + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}
