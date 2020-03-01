package chapter07.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import com.autumncode.hibernate.util.SessionUtil;

import chapter07.unvalidated.UnvalidatedSimplePerson;
import chapter07.validated.ValidatedSimplePerson;

public class ValidatorTest {
    @Test
    public void createUnvalidatedUnderagePerson() {
        //Long id = null;
        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();

            UnvalidatedSimplePerson person = new UnvalidatedSimplePerson();
            person.setAge(12); // underage for system
            person.setFname("Johnny");
            person.setLname("McYoungster");

            session.persist(person);
            //id = person.getId();
            transaction.commit();
        }
    }

    @Test
    public void createValidPerson() {
        persist(ValidatedSimplePerson.builder()
                .age(15)
                .fname("Johnny")
                .lname("McYoungster").build());
    }

    @Test
    public void createValidatedUnderagePerson() {
    	assertThrows(ConstraintViolationException.class, () -> {
    		persist(ValidatedSimplePerson.builder()
    				.age(12)
    				.fname("Johnny")
    				.lname("McYoungster").build());
    		fail("Should have failed validation");
    	});
    }

    @Test
    public void createValidatedPoorFNamePerson2() {
    	assertThrows(ConstraintViolationException.class, () -> {
    		persist(ValidatedSimplePerson.builder()
    				.age(14)
    				.fname("J")
    				.lname("McYoungster2").build());
    		fail("Should have failed validation");
    	});
    }

    @Test
    public void createValidatedNoFNamePerson() {
    	assertThrows(ConstraintViolationException.class, () -> {
    		persist(ValidatedSimplePerson.builder()
    				.age(14)
    				.lname("McYoungster2").build());
    		fail("Should have failed validation");
    	}
    			);
    }

    private ValidatedSimplePerson persist(ValidatedSimplePerson person) {
        try (Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(person);
            tx.commit();
        }
        return person;
    }

}
