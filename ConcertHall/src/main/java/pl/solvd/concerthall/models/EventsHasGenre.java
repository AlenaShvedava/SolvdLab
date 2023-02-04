package pl.solvd.concerthall.models;
public class EventsHasGenre  {
    private Long eventsId;
    private Long genreId;

    public EventsHasGenre() {
    }

    public EventsHasGenre(Long eventsId, Long genreId) {
        this.eventsId = eventsId;
        this.genreId = genreId;
    }

    public Long getEventsId() {
        return eventsId;
    }

    public void setEventsId(Long eventsId) {
        this.eventsId = eventsId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "EventsHasGenre{" +
                "eventsId=" + eventsId +
                ", genreId=" + genreId +
                '}';
    }
}
