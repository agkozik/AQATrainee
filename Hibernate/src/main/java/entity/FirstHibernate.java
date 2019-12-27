package entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FirstHibernate {
    public static void main(String[] args) {
        Person rick = new Person();
        rick.setName("Rick");
        rick.setLastName("Smith");
        rick.setAge(70);
        rick.setIq(100.0);

        Person morty = new Person(1,"Morty","Smith",14,14.0);
        Person bet = new Person(2,"Bet","Smith",45,90);
        Person jerry = new Person(3,"Jerry","Smith",45,5);
        insertIntoTable(rick);
        insertIntoTable(morty);
        insertIntoTable(bet);
        insertIntoTable(jerry);

    }

    private static void insertIntoTable(Person x) {
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session =sessionFactory
                .openSession();
        session.beginTransaction();
        session.save(x);
        session.getTransaction().commit();
        session.close();
    }

}
