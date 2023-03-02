package pl.solvd.concerthalls.binary;

import java.util.List;

public class Poster {
    private Long id;
    private int year;
    private int month;
    private int day;
    private double time;
    List<Program> program;

    public Poster() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public List<Program> getProgram() {
        return program;
    }

    public void setProgram(List<Program> program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "Poster №" + id + ": \n" +
                "Date: " + year + "/" + month + "/" + day + "\n" +
                "Time: " + String.format("%.2f", time) + "\n" +
                program.toString().replace("[", "").replace("]", "").replace(",", "");
    }
}
