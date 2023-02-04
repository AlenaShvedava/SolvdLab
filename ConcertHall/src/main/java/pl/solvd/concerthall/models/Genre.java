package pl.solvd.concerthall.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "genre")
public class Genre {
    private Long id;
    private String type;

    public Genre() {
    }

    public Genre(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    @XmlAttribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "\nGenre: " + type +"\n";
    }
}
