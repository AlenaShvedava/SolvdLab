package pl.solvd.concerthall.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "poster")
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

    @XmlAttribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @XmlElement(name = "month")
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @XmlElement(name = "day")
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @XmlElement(name = "time")
    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @XmlElement(name = "program")
    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "Poster №"+ id + ": \n" +
                "Date: " + year + "/" + month + "/" + day + "\n" +
                "Time: " + time + "\n" +
                program.toString().replace("[", "").replace("]", "").replace(",", "");
    }
}
