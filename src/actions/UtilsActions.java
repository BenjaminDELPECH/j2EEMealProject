package actions;

import entity.MealEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UtilsActions {
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");

    static EntityManager getEntityManager() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        return em;
    }


    static void endTransaction(EntityManager em) {
        em.getTransaction().commit();
        em.close();
    }

    public static void update(Object item) {
        EntityManager em = getEntityManager();
        em.merge(item);
        endTransaction(em);
    }

    public static void delete(Object myObj, int objId){
        EntityManager em = getEntityManager();
        em.remove(em.find(myObj.getClass(), objId));
        endTransaction(em);
    }
}
