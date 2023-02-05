package pl.solvd.concerthall.models;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    @JsonProperty("phone")
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
