package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

public class EventsHasGenreEntity extends MySqlDAO {
    private Long eventsId;
    private Long genreId;

    public EventsHasGenreEntity() {
    }

    public EventsHasGenreEntity(Long eventsId, Long genreId) {
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
