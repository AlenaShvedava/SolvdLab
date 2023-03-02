package pl.solvd.concerthalls.binary;

import java.util.List;

public class AuthorTypes {
    private Long id;
    private String type;
    private List<Authors> authors;

    public AuthorTypes() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Authors> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return type + '\n';
    }
}
