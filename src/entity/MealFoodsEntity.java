package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "meal_foods", schema = "mealcreator")
@IdClass(MealFoodsEntityPK.class)
public class MealFoodsEntity {
    private int mealId;
    private int foodId;

    @Id
    @Column(name = "meal_id")
    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    @Id
    @Column(name = "food_id")
    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealFoodsEntity that = (MealFoodsEntity) o;
        return mealId == that.mealId &&
                foodId == that.foodId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealId, foodId);
    }
}
