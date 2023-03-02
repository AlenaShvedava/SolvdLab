package pl.solvd.concerthalls.binary;

import java.util.List;

public class Composition {
    private Long id;
    private String title;
    private List<Authors> authors;
    private List<Program> program;

    public Composition() {
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

    public List<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Authors> authors) {
        this.authors = authors;
    }

    public List<Program> getProgram() {
        return program;
    }

    public void setProgram(List<Program> program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "Composition: \n" +
                "Title: " + title + "\n" +
                "Authors: " + authors + "\n";
    }
}
