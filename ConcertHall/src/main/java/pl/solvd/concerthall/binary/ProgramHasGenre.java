package pl.solvd.concerthall.binary;

public class ProgramHasGenre {
    private Long programId;
    private Long genreId;

    public ProgramHasGenre() {
    }

    public ProgramHasGenre(Long programId, Long genreId) {
        this.programId = programId;
        this.genreId = genreId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "ProgramHasGenre{" +
                "programId=" + programId +
                ", genreId=" + genreId +
                '}';
    }
}
