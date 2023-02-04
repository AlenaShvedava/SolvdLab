package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

public class ImagesEntity extends MySqlDAO {
    private Long id;
    private String imagePath;
    private Long programId;
    private boolean isPrimary;

    public ImagesEntity() {
    }

    public ImagesEntity(Long id, String imagePath, Long programId, boolean isPrimary) {
        this.id = id;
        this.imagePath = imagePath;
        this.programId = programId;
        this.isPrimary = isPrimary;
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
