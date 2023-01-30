package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

import java.util.List;

public class MySeat extends MySqlDAO {
    private Long id;
    private int rowNumber;
    private int seatNumber;
    private int numOfSeatsId;

    public MySeat() {
    }

    public MySeat(Long id, int rowNumber, int seatNumber, int numOfSeatsId) {
        this.id = id;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.numOfSeatsId = numOfSeatsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getNumOfSeatsId() {
        return numOfSeatsId;
    }

    public void setNumOfSeatsId(int numOfSeatsId) {
        this.numOfSeatsId = numOfSeatsId;
    }

    @Override
    public String toString() {
        return "MySeat{" +
                "id=" + id +
                ", rowNumber=" + rowNumber +
                ", seatNumber=" + seatNumber +
                ", numOfSeatsId=" + numOfSeatsId +
                '}';
    }
}
