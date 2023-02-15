package pl.solvd.concerthalls.binary;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AuthorTypes extends Base {
   // private static Long id;
    private String type;
    private List<Authors> authors;

//    public AuthorTypes() {
//    }

    public AuthorTypes(Long id, String type) {
        authors = new ArrayList<>();
        this.setId(id);
        this.type = type;
    }
//
//    public static Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorTypes that = (AuthorTypes) o;
        return getId() == that.getId() && Objects.equals(type, that.type) && Objects.equals(authors, that.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), type, authors);
    }

    @Override
    public String toString() {
        return type + '\n';
    }
}
