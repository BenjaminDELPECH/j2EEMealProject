package actions;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
}
