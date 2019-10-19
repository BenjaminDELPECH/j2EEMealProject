package staticObjectForFrontend;

public class NutrientStats {
    private String nutrientName;
    private int nutrientSumValue;
    private float requirement;
    private String unit;


    public NutrientStats(String nutrientName, int nutrientSumValue, float requirement, String unit) {
        this.nutrientName = nutrientName;
        this.nutrientSumValue = nutrientSumValue;
        this.requirement = requirement;
        this.unit = unit;
    }

    public String getNutrientName() {
        return nutrientName;
    }

    public void setNutrientName(String nutrientName) {
        this.nutrientName = nutrientName;
    }

    public int getNutrientSumValue() {
        return nutrientSumValue;
    }

    public void setNutrientSumValue(int nutrientSumValue) {
        this.nutrientSumValue = nutrientSumValue;
    }

    public float getRequirement() {
        return requirement;
    }

    public void setRequirement(float requirement) {
        this.requirement = requirement;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
