package pl.solvd.concerthall.models;

public class NumOfSeats {
    private Long id;
    private Long concertHallsId;
    private int priceLevelId;
    private int amountOfSeats;

    public NumOfSeats(Long id, Long concertHallsId, int priceLevelId, int amountOfSeats) {
        this.id = id;
        this.concertHallsId = concertHallsId;
        this.priceLevelId = priceLevelId;
        this.amountOfSeats = amountOfSeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConcertHallsId() {
        return concertHallsId;
    }

    public void setConcertHallsId(Long concertHallsId) {
        this.concertHallsId = concertHallsId;
    }

    public int getPriceLevelId() {
        return priceLevelId;
    }

    public void setPriceLevelId(int priceLevelId) {
        this.priceLevelId = priceLevelId;
    }

    public int getAmountOfSeats() {
        return amountOfSeats;
    }

    public void setAmountOfSeats(int amountOfSeats) {
        this.amountOfSeats = amountOfSeats;
    }

    @Override
    public String toString() {
        return "NumOfSeats{" +
                "id=" + id +
                ", concertHallsId=" + concertHallsId +
                ", priceLevelId=" + priceLevelId +
                ", amountOfSeats=" + amountOfSeats +
                '}';
    }
}
