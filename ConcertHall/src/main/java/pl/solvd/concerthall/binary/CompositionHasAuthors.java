package pl.solvd.concerthall.binary;

public class CompositionHasAuthors {
    private Long compositionId;
    private Long authorsId;

    public CompositionHasAuthors() {
    }

    public CompositionHasAuthors(Long compositionId, Long authorsId) {
        this.compositionId = compositionId;
        this.authorsId = authorsId;
    }

    public Long getCompositionId() {
        return compositionId;
    }

    public void setCompositionId(Long compositionId) {
        this.compositionId = compositionId;
    }

    public Long getAuthorsId() {
        return authorsId;
    }

    public void setAuthorsId(Long authorsId) {
        this.authorsId = authorsId;
    }

    @Override
    public String toString() {
        return "CompositionHasAuthors{" +
                "compositionId=" + compositionId +
                ", authorsId=" + authorsId +
                '}';
    }
}
