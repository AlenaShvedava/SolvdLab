package pl.solvd.concerthalls.binary;

public class MySeat {
    private Long id;
    private int rowNumber;
    private int seatNumber;
    private int numOfSeatsId;

    public MySeat() {
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
