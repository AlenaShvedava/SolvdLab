package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

import java.util.ArrayList;
import java.util.List;

public class ConcertHalls extends MySqlDAO {
    private static Long id;
    private String name;
    private String phone;
    private String address;
    private int sumNumberOfSeats;
    List<Program> program;

    public ConcertHalls() {
    }

    public ConcertHalls(Long id, String name, String address, String phone, int sumNumberOfSeats) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.sumNumberOfSeats = sumNumberOfSeats;
        this.phone = phone;
        program = new ArrayList<>();
    }

    public static Long getId() {
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
