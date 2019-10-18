package staticObjectForFrontend;

public class NutrientStats {
    private String nutrientName;
    private Double nutrientSumValue;
    private Float percentage;

    public NutrientStats(String nutrientName, Double nutrientSumValue) {
        this.nutrientName = nutrientName;
        this.nutrientSumValue = nutrientSumValue;
        this.percentage = 50f;
    }

    public String getNutrientName() {
        return nutrientName;
    }

    public void setNutrientName(String nutrientName) {
        this.nutrientName = nutrientName;
    }

    public Double getNutrientSumValue() {
        return nutrientSumValue;
    }

    public void setNutrientSumValue(Double nutrientSumValue) {
        this.nutrientSumValue = nutrientSumValue;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }
}
