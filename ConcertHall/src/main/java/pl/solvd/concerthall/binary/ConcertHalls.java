package pl.solvd.concerthall.binary;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "concertHalls")
public class ConcertHalls {
    @XmlAttribute
    private static Long id;
    private String name;
    private String phone;
    private String address;
    private int sumNumberOfSeats;
    List<Program> program;

    public ConcertHalls() {
    }

    public ConcertHalls(Long id, String name, String phone, String address, int sumNumberOfSeats) {
        ConcertHalls.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.sumNumberOfSeats = sumNumberOfSeats;
    }

    @JsonProperty("id")
    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        ConcertHalls.id = id;
    }
    @XmlElement(name = "name")
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement(name = "address")
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSumNumberOfSeats() {
        return sumNumberOfSeats;
    }

    public void setSumNumberOfSeats(int sumNumberOfSeats) {
        this.sumNumberOfSeats = sumNumberOfSeats;
    }
    @XmlElement(name = "phone")
    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Program> getProgram() {
        return program;
    }

    public void setProgram(List<Program> program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "\nConcert Hall: " + name + "\n" +
                "Address: " + address + "\n" +
                "Phone: " + phone + "\n" +
                "=====================================";
    }
}
