package actions;

import entity.*;
import staticObjectForFrontend.NutrientStats;
import view.MealFoodView;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Named
public class MealFoodViewActions {

    @Inject
    MealFoodView mealFoodView;

    public static MealEntity getMeal(int primaryKey) {
        EntityManager em = UtilsActions.getEntityManager();
        MealEntity meal = em.find(MealEntity.class, primaryKey);
        UtilsActions.checkIfItemWasReturned(MealEntity.class, primaryKey, meal);
        UtilsActions.endTransaction(em);
        return meal;
    }

    public void insertFoodInMeal(int foodId, int mealId) {
        MealFoodsEntity newMealFoodsEntity = new MealFoodsEntity();
        newMealFoodsEntity.setFoodId(foodId);
        newMealFoodsEntity.setMealId(mealId);
        EntityManager em = UtilsActions.getEntityManager();
        em.persist(newMealFoodsEntity);
        UtilsActions.endTransaction(em);
        mealFoodView.setFoodList(getFoodMealList(mealId));
        mealFoodView.setNutrientSumValues(getNutrientStatsValues());
    }

    public static MealFoodsEntity getFoodMeal(int foodId, int mealId) {
        EntityManager em = UtilsActions.getEntityManager();
        Query query = em.createQuery("from MealFoodsEntity where foodId = :foodId and mealId = :mealId ");
        query.setParameter("foodId", foodId);
        query.setParameter("mealId", mealId);
        MealFoodsEntity mealFoodsEntity = null;
        if (query.getResultList() != null || !query.getResultList().isEmpty()) {
            mealFoodsEntity = (MealFoodsEntity) query.getResultList().get(0);
        }
        UtilsActions.endTransaction(em);
        return mealFoodsEntity;
    }


    public static List<FoodsFoodEntity> getFoodMealList(int mealId) {
        EntityManager em = UtilsActions.getEntityManager();
        Query query = em.createQuery("Select foodId from MealFoodsEntity where mealId = :mealId");
        query.setParameter("mealId", mealId);
        List<FoodsFoodEntity> results = null;
        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            Query query2 = em.createQuery("from FoodsFoodEntity where id in :foodIdList");
            query2.setParameter("foodIdList", query.getResultList());
            results = query2.getResultList();
        }
        UtilsActions.endTransaction(em);
        return results;
    }

    public static List<FoodsFoodEntity> getFoodSearchList(String textSearch) {
        EntityManager em = UtilsActions.getEntityManager();
        Query query = em.createQuery("from FoodsFoodEntity where nameFr like :textSearch");
        query.setParameter("textSearch", "%" + textSearch + "%");
        List<FoodsFoodEntity> results = null;
        results = query.getResultList();
        UtilsActions.endTransaction(em);
        return results;
    }

    public void deleteMealFood(FoodsFoodEntity food, int mealId) {
        int foodId = food.getId();
        MealFoodsEntity mealFoodsEntity = MealFoodViewActions.getFoodMeal(foodId, mealId);
        MealFoodsEntityPK mealFoodsEntityPK = new MealFoodsEntityPK();
        mealFoodsEntityPK.setFoodId(foodId);
        mealFoodsEntityPK.setMealId(mealId);
        UtilsActions.delete(mealFoodsEntity, mealFoodsEntityPK);
        mealFoodView.getFoodList().remove(food);
        mealFoodView.setNutrientSumValues(getNutrientStatsValues());
    }


    public static List<NutrientStats> getNutrientStatsValues() {
        List<NutrientStats> nutrientStatsList = new ArrayList<NutrientStats>();
        List<Tuple> tuples = getNutrientStatsFromDB();
        if (tuples != null && !tuples.isEmpty()) {
            generateNutrientStatList(nutrientStatsList, tuples);
        }
        return nutrientStatsList;
    }

    public static List<Tuple> getNutrientStatsFromDB() {
        EntityManager em = UtilsActions.getEntityManager();
        Query query = em.createQuery("Select foodId from MealFoodsEntity where mealId = :mealId");
        query.setParameter("mealId", 482);
        List<Tuple> results= null;
        if(query.getResultList()!=null && !query.getResultList().isEmpty()){
            Query query2 = em.createQuery("Select sum (nv.value), n.name from NutrientValuesEntity nv, NutrientEntity n where nv.nutrientId = n.id and nv.foodId in :foodList group by nv.nutrientId", Tuple.class);
            query2.setParameter("foodList", query.getResultList());
            results =(List<Tuple>)query2.getResultList();
        }

        UtilsActions.endTransaction(em);
        return results;
    }

    public static void generateNutrientStatList(List<NutrientStats> nutrientStatsList, List<Tuple> tuples) {
        tuples.forEach(tuple -> {
            Double sumValue = (Double) tuple.get(0);
            String nutrient_name = (String) tuple.get(1);
            NutrientStats nutrientStats = new NutrientStats(nutrient_name, sumValue);
            nutrientStatsList.add(nutrientStats);
        });
    }

}
