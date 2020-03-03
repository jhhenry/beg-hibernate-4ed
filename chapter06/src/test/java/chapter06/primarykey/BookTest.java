package chapter06.primarykey;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import com.autumncode.hibernate.util.SessionUtil;

public class BookTest {
    @Test
    public void bookTest() {
        try (Session session = SessionUtil.getSession()) {
            assertNotNull(session);
        }
    }
}
