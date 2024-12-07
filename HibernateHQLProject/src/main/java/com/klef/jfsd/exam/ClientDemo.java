package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Client client1 = new Client();
            client1.setName("Alice");
            client1.setGender("Female");
            client1.setAge(30);
            client1.setLocation("New York");
            client1.setEmail("alice@example.com");
            client1.setMobile("1234567890");

            Client client2 = new Client();
            client2.setName("Bob");
            client2.setGender("Male");
            client2.setAge(35);
            client2.setLocation("Los Angeles");
            client2.setEmail("bob@example.com");
            client2.setMobile("0987654321");

            session.persist(client1);
            session.persist(client2);

            transaction.commit();

            List<Client> clients = session.createQuery("FROM Client", Client.class).getResultList();
            for (Client client : clients) {
                System.out.println(client);
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
