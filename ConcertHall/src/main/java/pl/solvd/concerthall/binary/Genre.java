package pl.solvd.concerthall.binary;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "genre")
public class Genre {
    private static Long id;
    private String type;
    List<Program> program;
    List<Events> event;

    public Genre() {
    }

    public Genre(Long id, String type) {
        Genre.id = id;
        this.type = type;
    }
    @JsonProperty("id")
    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        Genre.id = id;
    }
    @XmlElement(name = "type")
//    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Program> getProgram() {
        return program;
    }

    public void setProgram(List<Program> program) {
        this.program = program;
    }

    public List<Events> getEvent() {
        return event;
    }

    public void setEvent(List<Events> event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "\nGenre: " + type + "\n";
    }
}
