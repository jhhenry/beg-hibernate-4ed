package chapter08;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.autumncode.hibernate.util.SessionUtil;

import chapter08.model.Supplier;

public class QueryTest {
    @BeforeEach
    public void populateData() {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        Supplier supplier = new Supplier("Hardware, Inc.");
        session.save(supplier);

        supplier = new Supplier("Supplier 2");

        session.save(supplier);
        tx.commit();
        session.close();
    }

    @AfterEach
    public void closeSession() {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        session.createQuery("delete from Supplier").executeUpdate();
        tx.commit();
        session.close();
    }

    @Test
    public void testSuppliers() {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        Supplier supplier = session.byId(Supplier.class).load(1);
        tx.commit();
        session.close();

        session = SessionUtil.getSession();
        tx = session.beginTransaction();

        supplier = session.byId(Supplier.class).load(1);

        tx.commit();
        session.close();
    }
}
