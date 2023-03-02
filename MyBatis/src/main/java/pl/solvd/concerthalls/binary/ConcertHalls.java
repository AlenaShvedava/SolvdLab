package pl.solvd.concerthalls.binary;

import java.util.List;

public class ConcertHalls {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private int sumNumberOfSeats;
    List<Program> program;

    public ConcertHalls() {
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
