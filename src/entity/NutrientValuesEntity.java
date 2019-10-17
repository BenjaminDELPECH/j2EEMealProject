package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "nutrient_values", schema = "mealcreator", catalog = "")
@IdClass(NutrientValuesEntityPK.class)
public class NutrientValuesEntity {
    private int foodId;
    private int nutrientId;
    private Double value;

    @Id
    @Column(name = "food_id")
    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    @Id
    @Column(name = "nutrient_id")
    public int getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(int nutrientId) {
        this.nutrientId = nutrientId;
    }

    @Basic
    @Column(name = "value")
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NutrientValuesEntity that = (NutrientValuesEntity) o;
        return foodId == that.foodId &&
                nutrientId == that.nutrientId &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodId, nutrientId, value);
    }
}
