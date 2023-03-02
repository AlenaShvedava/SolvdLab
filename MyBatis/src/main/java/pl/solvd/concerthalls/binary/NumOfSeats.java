package pl.solvd.concerthalls.binary;

public class NumOfSeats {
    private Long id;
    private Long concertHallsId;
    private Long priceLevelId;
    private int amountOfSeats;

    public NumOfSeats() {
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

    public Long getPriceLevelId() {
        return priceLevelId;
    }

    public void setPriceLevelId(Long priceLevelId) {
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
