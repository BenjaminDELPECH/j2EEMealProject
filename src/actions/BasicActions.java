package actions;

import entity.MealEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class BasicActions {
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");

    public static void update(Object item) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(item);
        em.getTransaction().commit();
        em.close();
    }

    public static void delete(Object myObj, int objId){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(myObj.getClass(), objId));
        em.getTransaction().commit();
        em.close();
    }
}
