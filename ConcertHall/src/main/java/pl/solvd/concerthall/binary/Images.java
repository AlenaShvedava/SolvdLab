package pl.solvd.concerthall.binary;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "images")
public class Images {
    private static Long id;
    private String imagePath;
    private Long programId;
    private boolean isPrimary;

    public Images() {
    }

    public Images(Long id, String imagePath, Long programId, boolean isPrimary) {
        Images.id = id;
        this.imagePath = imagePath;
        this.programId = programId;
        this.isPrimary = isPrimary;
    }
    @XmlAttribute
    @JsonProperty("id")
    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        Images.id = id;
    }
    @XmlElement(name = "imagepath")
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
