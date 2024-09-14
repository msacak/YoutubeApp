package org.example.utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateConnection {

    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu_hibernate");
    public static EntityManager entityManager = entityManagerFactory.createEntityManager();


}
