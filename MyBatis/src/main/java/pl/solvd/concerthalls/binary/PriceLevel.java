package pl.solvd.concerthalls.binary;

public class PriceLevel {
    private Long id;
    private String type;
    private double coefficient;

    public PriceLevel() {
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
