package pl.solvd.concerthall.entities;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;

import java.util.List;

public class PriceLevel extends MySqlDAO {
    private Long id;
    private String type;
    private double coefficient;

    public PriceLevel() {
    }

    public PriceLevel(Long id, String type, double coefficient) {
        this.id = id;
        this.type = type;
        this.coefficient = coefficient;
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

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "PriceLevel{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", coefficient=" + coefficient +
                '}';
    }
}
