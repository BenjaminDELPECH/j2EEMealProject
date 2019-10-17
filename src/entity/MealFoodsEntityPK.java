package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MealFoodsEntityPK implements Serializable {
    private int mealId;
    private int foodId;

    @Column(name = "meal_id")
    @Id
    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    @Column(name = "food_id")
    @Id
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
        MealFoodsEntityPK that = (MealFoodsEntityPK) o;
        return mealId == that.mealId &&
                foodId == that.foodId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealId, foodId);
    }
}
