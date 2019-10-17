package actions;

import entity.MealEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class MealFoodViewActions {
    public static MealEntity getMeal(int primaryKey){
        EntityManager em = UtilsActions.getEntityManager();
        MealEntity meal = em.find(MealEntity.class, primaryKey);
        UtilsActions.checkIfItemWasReturned(MealEntity.class, primaryKey, meal);
        UtilsActions.endTransaction(em);
        return meal;
    }


}
