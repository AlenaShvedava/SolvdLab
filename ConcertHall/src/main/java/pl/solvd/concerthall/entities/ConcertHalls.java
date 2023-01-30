package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.interfacesDAO.IConcertHallsDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;

import java.util.List;

public class ConcertHalls extends MySqlDAO {
    private Long id;
    private String name;
    private String address;
    private int sumNumberOfSeats;
    private String phone;

    public ConcertHalls() {
    }

    public ConcertHalls(Long id, String name, String address, int sumNumberOfSeats, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.sumNumberOfSeats = sumNumberOfSeats;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ConcertHalls{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", sumNumberOfSeats=" + sumNumberOfSeats +
                ", phone='" + phone + '\'' +
                '}';
    }
}

