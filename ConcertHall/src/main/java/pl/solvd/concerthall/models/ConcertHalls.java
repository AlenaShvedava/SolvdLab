package pl.solvd.concerthall.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "concerthalls")
public class ConcertHalls {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private int sumNumberOfSeats;


    public ConcertHalls() {
    }

    public ConcertHalls(Long id, String name, String address, int sumNumberOfSeats, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.sumNumberOfSeats = sumNumberOfSeats;
        this.phone = phone;
    }

    @XmlAttribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "address")
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
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "\nConcert Hall: " + name + "\n" +
                "Address: " + address + "\n" +
                "Phone: " + phone + "\n" +
                "=====================================";

    }
}
