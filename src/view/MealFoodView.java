package view;

import actions.MealFoodViewActions;
import actions.UtilsActions;
import entity.FoodEntity;
import entity.MealEntity;
import entity.MealFoodsEntity;
import entity.MealFoodsEntityPK;
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
import java.util.Map;

@ViewScoped
@Named
public class MealFoodView implements Serializable {
    private List<FoodEntity> foodList;
    private List<NutrientStats> nutrientSumValues = new ArrayList<NutrientStats>();;
    private MealEntity meal;

    @PostConstruct
    public void init() {
        String parameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("meal");
        int parameterInt = Integer.parseInt(parameter);
        meal = MealFoodViewActions.getMeal(parameterInt);
        foodList = MealFoodViewActions.getFoodMealList(meal.getId());
        getNutrientStatsValues();
    }

    public void deleteMealFood(Object myObj, int foodId) {
        foodList.remove(myObj);
        MealFoodViewActions.deleteMealFood(foodId, meal.getId());
    }

    public void getNutrientStatsValues() {
        EntityManager em = UtilsActions.getEntityManager();
        Query query = em.createQuery("Select foodId from MealFoodsEntity where mealId = :mealId");
        query.setParameter("mealId", 482);
        Query query2 = em.createQuery("Select sum (nv.value), n.name from NutrientValuesEntity nv, NutrientEntity n where nv.nutrientId = n.id and nv.foodId in :foodList group by nv.nutrientId", Tuple.class);
        query2.setParameter("foodList", query.getResultList());
        List<Tuple> tuples = query2.getResultList();
        if(tuples != null && !tuples.isEmpty()){

            System.out.println("fdp");
            tuples.forEach(tuple->{
                Double sumValue = (Double)tuple.get(0);
                String nutrient_name = (String)tuple.get(1);
                NutrientStats nutrientStats = new NutrientStats(nutrient_name, sumValue);
                nutrientSumValues.add(nutrientStats);
            });


        }
    }


    public MealEntity getMeal() {
        return meal;
    }

    public void setMeal(MealEntity meal) {
        this.meal = meal;
    }

    public List<FoodEntity> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodEntity> foodList) {
        this.foodList = foodList;
    }

    public List<NutrientStats> getNutrientSumValues() {
        return nutrientSumValues;
    }

    public void setNutrientSumValues(List<NutrientStats> nutrientSumValues) {
        this.nutrientSumValues = nutrientSumValues;
    }
}
