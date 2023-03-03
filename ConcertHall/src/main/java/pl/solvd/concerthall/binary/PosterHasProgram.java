package pl.solvd.concerthall.binary;

public class PosterHasProgram {
    private Long posterId;
    private Long programId;

    public PosterHasProgram() {
    }

    public PosterHasProgram(Long posterId, Long programId) {
        this.posterId = posterId;
        this.programId = programId;
    }

    public Long getPosterId() {
        return posterId;
    }

    public void setPosterId(Long posterId) {
        this.posterId = posterId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    @Override
    public String toString() {
        return "PosterHasProgram{" +
                "posterId=" + posterId +
                ", programId=" + programId +
                '}';
    }
}
