package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

import java.util.List;

public class ProgramHasComposition extends MySqlDAO {
    private Long programId;
    private Long compositionId;

    public ProgramHasComposition() {
    }

    public ProgramHasComposition(Long programId, Long compositionId) {
        this.programId = programId;
        this.compositionId = compositionId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Long getCompositionId() {
        return compositionId;
    }

    public void setCompositionId(Long compositionId) {
        this.compositionId = compositionId;
    }

    @Override
    public String toString() {
        return "ProgramHasComposition{" +
                "programId=" + programId +
                ", compositionId=" + compositionId +
                '}';
    }
}
