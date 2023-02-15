package pl.solvd.concerthalls.binary;

import java.util.Objects;

public class AuthorsHasAuthorTypes {
    private Long authorsId;
    private Long authorTypesId;

    public AuthorsHasAuthorTypes() {
    }

    public AuthorsHasAuthorTypes(Long authorsId, Long authorTypesId) {
        this.authorsId = authorsId;
        this.authorTypesId = authorTypesId;
    }

    public Long getAuthorsId() {
        return authorsId;
    }

    public void setAuthorsId(Long authorsId) {
        this.authorsId = authorsId;
    }

    public Long getAuthorTypesId() {
        return authorTypesId;
    }

    public void setAuthorTypesId(Long authorTypesId) {
        this.authorTypesId = authorTypesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorsHasAuthorTypes that = (AuthorsHasAuthorTypes) o;
        return Objects.equals(authorsId, that.authorsId) && Objects.equals(authorTypesId, that.authorTypesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorsId, authorTypesId);
    }

    @Override
    public String toString() {
        return "AuthorsHasAuthorTypes{" +
                "authorsId=" + authorsId +
                ", authorTypesId=" + authorTypesId +
                '}';
    }
}
