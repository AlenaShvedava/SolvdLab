package pl.solvd.concerthall.binary;

import java.util.List;

public class Organization {
    private static Long id;
    private String name;
    List<Program> program;

    public Organization() {
    }

    public Organization(Long id, String name) {
        Organization.id = id;
        this.name = name;
    }

    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        Organization.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Program> getProgram() {
        return program;
    }

    public void setProgram(List<Program> program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "\nOrganization: " + name + '\n';
    }
}
