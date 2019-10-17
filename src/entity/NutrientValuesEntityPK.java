package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class NutrientValuesEntityPK implements Serializable {
    private int foodId;
    private int nutrientId;

    @Column(name = "food_id")
    @Id
    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    @Column(name = "nutrient_id")
    @Id
    public int getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(int nutrientId) {
        this.nutrientId = nutrientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NutrientValuesEntityPK that = (NutrientValuesEntityPK) o;
        return foodId == that.foodId &&
                nutrientId == that.nutrientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodId, nutrientId);
    }
}
