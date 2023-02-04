package pl.solvd.concerthall.models;

public class Events {
    private Long id;
    private String category;

    public Events() {
    }

    public Events(Long id, String category) {
        this.id = id;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Events{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }
}
