package test;

import entity.MealEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        MealEntity mealEntity = new MealEntity();
        mealEntity.setName("test");
        em.persist(mealEntity);
        em.getTransaction().commit();
        em.close();
    }
}
