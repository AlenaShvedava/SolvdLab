package pl.solvd.concerthall.models;

public class Order {
    private Long id;
    private Long customerId;
    private Long posterId;
    private Long priceLevelId;
    private Long numberOfTickets;

    public Order() {
    }

    public Order(Long id, Long customerId, Long posterId, Long priceLevelId, Long numberOfTickets) {
        this.id = id;
        this.customerId = customerId;
        this.posterId = posterId;
        this.priceLevelId = priceLevelId;
        this.numberOfTickets = numberOfTickets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getPosterId() {
        return posterId;
    }

    public void setPosterId(Long posterId) {
        this.posterId = posterId;
    }

    public Long getPriceLevelId() {
        return priceLevelId;
    }

    public void setPriceLevelId(Long priceLevelId) {
        this.priceLevelId = priceLevelId;
    }

    public Long getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Long numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", posterId=" + posterId +
                ", priceLevelId=" + priceLevelId +
                ", numberOfTickets=" + numberOfTickets +
                '}';
    }
}
