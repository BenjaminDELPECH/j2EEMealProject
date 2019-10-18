package view;

import actions.MealFoodViewActions;
import actions.UtilsActions;
import entity.FoodsFoodEntity;
import entity.MealEntity;
import staticObjectForFrontend.NutrientStats;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named
public class MealFoodView implements Serializable {

    private List<FoodsFoodEntity> foodList = new ArrayList<>();
    private List<FoodsFoodEntity> searchFoodList = new ArrayList<>();
    private List<NutrientStats> nutrientSumValues = new ArrayList<NutrientStats>();
    private MealEntity meal;
    private String foodSearchText;

    @PostConstruct
    public void init() {
        String parameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("meal");
        int parameterInt = Integer.parseInt(parameter);
        meal = MealFoodViewActions.getMeal(parameterInt);
        foodList = MealFoodViewActions.getFoodMealList(parameterInt);
        nutrientSumValues = MealFoodViewActions.getNutrientStatsValues();
    }





    public void searchFood(){
        searchFoodList = MealFoodViewActions.getFoodSearchList(foodSearchText);
    }


    public MealEntity getMeal() {
        return meal;
    }

    public void setMeal(MealEntity meal) {
        this.meal = meal;
    }

    public List<FoodsFoodEntity> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodsFoodEntity> foodList) {
        this.foodList = foodList;
    }

    public List<NutrientStats> getNutrientSumValues() {
        return nutrientSumValues;
    }

    public void setNutrientSumValues(List<NutrientStats> nutrientSumValues) {
        this.nutrientSumValues = nutrientSumValues;
    }

    public List<FoodsFoodEntity> getSearchFoodList() {
        return searchFoodList;
    }

    public void setSearchFoodList(List<FoodsFoodEntity> searchFoodList) {
        this.searchFoodList = searchFoodList;
    }

    public String getFoodSearchText() {
        return foodSearchText;
    }

    public void setFoodSearchText(String foodSearchText) {
        this.foodSearchText = foodSearchText;
    }
}
