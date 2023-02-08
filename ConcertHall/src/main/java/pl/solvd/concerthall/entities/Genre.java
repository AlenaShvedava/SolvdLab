package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

import java.util.ArrayList;
import java.util.List;

public class Genre extends MySqlDAO {
    private static Long id;
    private String type;
    List<Program> program;

    public Genre() {
    }

    public Genre(Long id, String type) {
        this.id = id;
        this.type = type;
        program = new ArrayList<>();
    }

    public static Long getId() {
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

    @Override
    public String toString() {
        return "\nGenre: " + type + "\n";
    }
}
