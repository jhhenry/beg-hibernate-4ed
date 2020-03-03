package chapter02.hibernate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;

public class PersistenceTest {
    SessionFactory factory;

    public void setup() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void saveMessage() {
        Message message = new Message("Hello, world");
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(message);
            tx.commit();
        }
    }

    @Test
    public void readMessage() {
        try (Session session = factory.openSession()) {
            List<Message> list = session.createQuery("from Message", Message.class).list();

            assertEquals(list.size(), 1);
            for (Message m : list) {
                System.out.println(m);
            }
        }
    }
}
