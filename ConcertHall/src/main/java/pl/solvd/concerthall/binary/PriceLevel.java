package pl.solvd.concerthall.binary;

public class PriceLevel {
    private static Long id;
    private String type;
    private double coefficient;

    public PriceLevel() {
    }

    public PriceLevel(Long id, String type, double coefficient) {
        PriceLevel.id = id;
        this.type = type;
        this.coefficient = coefficient;
    }

    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        PriceLevel.id = id;
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
