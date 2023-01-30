package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

import java.util.List;

public class Ticket extends MySqlDAO {
    private Long id;
    private Long orderId;
    private Long mySeatId;
    private int price;
    private boolean active;

    public Ticket() {
    }

    public Ticket(Long id, Long orderId, Long mySeatId, int price, boolean active) {
        this.id = id;
        this.orderId = orderId;
        this.mySeatId = mySeatId;
        this.price = price;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getMySeatId() {
        return mySeatId;
    }

    public void setMySeatId(Long mySeatId) {
        this.mySeatId = mySeatId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", mySeatId=" + mySeatId +
                ", price=" + price +
                ", active=" + active +
                '}';
    }
}
