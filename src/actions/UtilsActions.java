package actions;

import entity.MealEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

public final class UtilsActions {
    private UtilsActions() {
    }

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");

    public static EntityManager getEntityManager() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        return em;
    }


    public static void endTransaction(EntityManager em) {
        em.getTransaction().commit();
        em.close();
    }

    public static Object get(Object entityClass , Object primaryKey){
        EntityManager em = getEntityManager();
        Object item = em.find(entityClass.getClass(), primaryKey);
        checkIfItemWasReturned(entityClass, primaryKey, item);
        endTransaction(em);
        return item;
    }

    public static void checkIfItemWasReturned(Object entityClass, Object primaryKey, Object item) {
        if(item == null){
            throw new EntityNotFoundException("Item "+entityClass + "+not found : "+ primaryKey);
        }
    }


    public static void update(Object item) {
        EntityManager em = getEntityManager();
        em.merge(item);
        endTransaction(em);
    }

    public static void delete(Object myObj, Object primaryKey) {
        EntityManager em = getEntityManager();
        em.remove(em.find(myObj.getClass(), primaryKey));
        endTransaction(em);
    }
}
