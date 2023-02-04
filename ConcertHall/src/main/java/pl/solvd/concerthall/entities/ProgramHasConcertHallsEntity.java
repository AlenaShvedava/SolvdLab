package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

public class ProgramHasConcertHallsEntity extends MySqlDAO {
    private Long programId;
    private Long concertHallsId;

    public ProgramHasConcertHallsEntity() {
    }

    public ProgramHasConcertHallsEntity(Long programId, Long concertHallsId) {
        this.programId = programId;
        this.concertHallsId = concertHallsId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Long getConcertHallsId() {
        return concertHallsId;
    }

    public void setConcertHallsId(Long concertHallsId) {
        this.concertHallsId = concertHallsId;
    }

    @Override
    public String toString() {
        return "ProgramHasConcertHalls{" +
                "programId=" + programId +
                ", concertHallsId=" + concertHallsId +
                '}';
    }
}
