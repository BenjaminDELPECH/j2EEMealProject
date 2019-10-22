package test;

import actions.UtilsActions;

import javax.persistence.*;

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
