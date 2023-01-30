package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

import java.util.List;

public class Images extends MySqlDAO {
    private Long id;
    private String imagePath;
    private Long programId;
    private boolean isPrimary;

    public Images() {
    }

    public Images(Long id, String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
    }

    public Long getId() {
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
        return "Images{" +
                "id=" + id +
                ", imagePath='" + imagePath + '\'' +
                ", programId=" + programId +
                ", isPrimary=" + isPrimary +
                '}';
    }
}
