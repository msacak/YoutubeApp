package org.example;

import org.example.gui.MainGUI;
import org.example.repository.*;


import org.example.service.UserService;
import org.example.utility.HibernateConnection;

public class Runner {
    public static void main(String[] args) {

        HibernateConnection.entityManager.getTransaction().begin();
        MainGUI.getInstance().mainGUI();
        HibernateConnection.entityManager.getTransaction().commit();

        HibernateConnection.entityManager.close();
        HibernateConnection.entityManagerFactory.close();










    }
}