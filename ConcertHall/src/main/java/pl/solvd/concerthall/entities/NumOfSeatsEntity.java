package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

public class NumOfSeatsEntity extends MySqlDAO {
    private Long id;
    private Long concertHallsId;
    private Long priceLevelId;
    private int amountOfSeats;

    public NumOfSeatsEntity() {
    }

    public NumOfSeatsEntity(Long id, Long concertHallsId, Long priceLevelId, int amountOfSeats) {
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
