package pl.solvd.concerthall.binary;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="poster")
public class Poster {
    @XmlAttribute
    private static Long id;
    private int year;
    private int month;
    private int day;
    private double time;
    List<Program> program;

    public Poster() {
    }

    public Poster(Long id, int year, int month, int day, double time) {
        Poster.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
    }

    @JsonProperty("id")
    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        Poster.id = id;
    }

    @XmlElement(name = "year")
    @JsonProperty("year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @XmlElement(name = "month")
    @JsonProperty("month")
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @XmlElement(name = "day")
    @JsonProperty("day")
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @XmlElement(name = "time")
    @JsonProperty("time")
    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @XmlElement(name = "program")
    @JsonProperty("program")
    public List<Program> getProgram() {
        return program;
    }

    public void setProgram(List<Program> program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "Poster № " + id + ": \n" +
                "Date: " + year + "/" + month + "/" + day + "\n" +
                "Time: " + String.format("%.2f", time) + "\n" +
                program.toString().replace("[", "").replace("]", "").replace(",", "");
    }
}
