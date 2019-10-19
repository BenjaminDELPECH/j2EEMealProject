package test;

import actions.UtilsActions;
import entity.MealEntity;
import entity.MealFoodsEntity;
import staticObjectForFrontend.NutrientStats;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static actions.MealFoodViewActions.generateNutrientStatList;
import static actions.MealFoodViewActions.getNutrientStatsFromDB;

public class Main {
    public static void main(String[] args) {
        Float requirement = 3000f;
        EntityManager em = UtilsActions.getEntityManager();
        Query query = em.createQuery("Select value from NutrientsRequirementsEntity  where nutrientId = :nutrientId");
        query.setParameter("nutrientId", 1166);
        if(query.getResultList() != null && !query.getResultList().isEmpty()){
            requirement =Float.valueOf(String.valueOf(query.getResultList().get(0)));
        }
        UtilsActions.endTransaction(em);
        System.out.println(requirement);
    }
}
