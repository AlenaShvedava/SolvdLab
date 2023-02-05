package pl.solvd.concerthall.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Images {
    private Long id;
    private String imagePath;
    private Long programId;
    private boolean isPrimary;

    public Images() {
    }

    public Images(Long id, String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("imagepath")
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    @Override
    public String toString() {
        return imagePath + '\n';
    }
}
