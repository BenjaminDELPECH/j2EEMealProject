package actions;

import entity.MealEntity;
import view.MealView;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named
public class MealActions implements Serializable {


    @Inject
    MealView mealView;


    public void insert(){
        EntityManager em = UtilsActions.getEntityManager();
        em.persist(mealView.getMeal());
        UtilsActions.endTransaction(em);
        mealView.setMeal(new MealEntity());
        mealView.setMealList(getAll());
    }

    public static List<MealEntity> getAll(){
        EntityManager em = UtilsActions.getEntityManager();
        List<MealEntity> mealList = em.createQuery("from MealEntity ", MealEntity.class).getResultList();
        UtilsActions.endTransaction(em);
        return mealList;
    }
}
