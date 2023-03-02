package pl.solvd.concerthalls.binary;

import java.util.List;

public class Genre {
    private Long id;
    private String type;
    List<Program> program;
    List<Events> event;

    public Genre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
