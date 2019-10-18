package actions;

import entity.FoodEntity;
import entity.MealEntity;
import entity.MealFoodsEntity;
import entity.MealFoodsEntityPK;
import view.MealFoodView;
import view.UserView;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.util.List;

public class MealFoodViewActions {


    public static MealEntity getMeal(int primaryKey){
        EntityManager em = UtilsActions.getEntityManager();
        MealEntity meal = em.find(MealEntity.class, primaryKey);
        UtilsActions.checkIfItemWasReturned(MealEntity.class, primaryKey, meal);
        UtilsActions.endTransaction(em);
        return meal;
    }

    public static MealFoodsEntity getFoodMeal(int foodId, int mealId){
        EntityManager em = UtilsActions.getEntityManager();
        Query query = em.createQuery("from MealFoodsEntity where foodId = :foodId and mealId = :mealId ");
        query.setParameter("foodId", foodId);
        query.setParameter("mealId", mealId);
        MealFoodsEntity mealFoodsEntity = null;
        if(query.getResultList() != null || !query.getResultList().isEmpty()) {
            mealFoodsEntity = (MealFoodsEntity)query.getResultList().get(0);
        }
        return mealFoodsEntity;
    }



    public static List<FoodEntity> getFoodMealList(int mealId){
        EntityManager em = UtilsActions.getEntityManager();
        Query query = em.createQuery("Select foodId from MealFoodsEntity where mealId = :mealId");
        query.setParameter("mealId", mealId);


        Query query2 = em.createQuery("from FoodEntity where id in :foodIdList");
        query2.setParameter("foodIdList", query.getResultList());
        return query2.getResultList();
    }

    public static void deleteMealFood(int foodId, int mealId) {
        MealFoodsEntity mealFoodsEntity = MealFoodViewActions.getFoodMeal(foodId, mealId);
        MealFoodsEntityPK mealFoodsEntityPK = new MealFoodsEntityPK();
        mealFoodsEntityPK.setFoodId(foodId);
        mealFoodsEntityPK.setMealId(mealId);
        UtilsActions.delete(mealFoodsEntity, mealFoodsEntityPK);
    }

}
