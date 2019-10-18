package test;

import actions.UtilsActions;
import entity.MealEntity;
import staticObjectForFrontend.NutrientStats;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<NutrientStats> nutrientSumValues = new ArrayList<NutrientStats>();

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
}
