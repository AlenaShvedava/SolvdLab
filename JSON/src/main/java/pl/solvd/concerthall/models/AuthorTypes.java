package pl.solvd.concerthall.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorTypes {
    private Long id;
    private String type;

    public AuthorTypes() {
    }

    public AuthorTypes(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type + '\n';
    }
}
