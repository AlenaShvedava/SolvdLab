package pl.solvd.concerthall.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Poster {
    private Long id;
    private int year;
    private int month;
    private int day;
    private double time;
    Program program;

    public Poster() {
    }

    public Poster(Long id, int year, int month, int day, double time, Program program) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
        this.program = program;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @JsonProperty("month")
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @JsonProperty("day")
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @JsonProperty("time")
    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @JsonProperty("program")
    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "\n\nPoster №" + id + ": \n" +
                "Date: " + year + "/" + month + "/" + day + "\n" +
                "Time: " + String.format("%.2f", time) + "\n" +
                program.toString().replace("[", "").replace("]", "").replace(",", "");
    }
}
