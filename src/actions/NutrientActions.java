package actions;

import staticObjectForFrontend.NutrientStats;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.util.List;

public class NutrientActions {
    public static float getNutrientRequirement(int nutrientId){
        float requirement = 0;
        EntityManager em = UtilsActions.getEntityManager();
        Query query = em.createQuery("Select value from NutrientsRequirementsEntity  where nutrientId = :nutrientId");
        query.setParameter("nutrientId", nutrientId);
        if(query.getResultList() != null && !query.getResultList().isEmpty()){
            requirement =(float)Float.valueOf(String.valueOf(query.getResultList().get(0)));
        }
        UtilsActions.endTransaction(em);
        return requirement;
    }

    public static String getNutrientUnit(int nutrientId){
        String unit = "g";
        EntityManager em = UtilsActions.getEntityManager();

        UtilsActions.endTransaction(em);
        return unit;
    }

    public static void generateNutrientStatList(List<NutrientStats> nutrientStatsList, List<Tuple> tuples) {
        tuples.forEach(tuple -> {
            Double sumValue = (Double) tuple.get(0);
            int nutrientId = (int)tuple.get(2);
            int sumValueRounded =(int) Math.round(sumValue);
            float nutrientRequirement = getNutrientRequirement(nutrientId);
            String unit = getNutrientUnit(nutrientId);
            String nutrient_name = (String) tuple.get(1);
            NutrientStats nutrientStats = new NutrientStats(nutrient_name, sumValueRounded, nutrientRequirement, unit);
            nutrientStatsList.add(nutrientStats);
        });
    }
}
