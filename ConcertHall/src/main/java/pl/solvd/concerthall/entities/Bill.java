package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

public class Bill extends MySqlDAO {
    private static Long id;
    private Long OrderId;
    private double totalPrice;
    private String paymentStatus;
    private boolean active;

    public Bill() {
    }

    public Bill(Long id, Long orderId, double totalPrice, String paymentStatus, boolean active) {
        this.id = id;
        OrderId = orderId;
        this.totalPrice = totalPrice;
        this.paymentStatus = paymentStatus;
        this.active = active;
    }

    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", OrderId=" + OrderId +
                ", totalPrice=" + totalPrice +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", active=" + active +
                '}';
    }
}
