package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

public class Images extends MySqlDAO {
    private static Long id;
    private String imagePath;
    private Long programId;
    private boolean isPrimary;

    public Images() {
    }

    public Images(Long id, String imagePath, Long programId, boolean isPrimary) {
        this.id = id;
        this.imagePath = imagePath;
        this.programId = programId;
        this.isPrimary = isPrimary;
    }

    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    @Override
    public String toString() {
        return imagePath + '\n';
    }
}
