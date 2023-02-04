package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

public class AuthorsHasAuthorTypesEntity extends MySqlDAO {
    private Long authorsId;
    private Long authorTypesId;

    public AuthorsHasAuthorTypesEntity() {
    }

    public AuthorsHasAuthorTypesEntity(Long authorsId, Long authorTypesId) {
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
    public String toString() {
        return "AuthorsHasAuthorTypes{" +
                "authorsId=" + authorsId +
                ", authorTypesId=" + authorTypesId +
                '}';
    }
}
