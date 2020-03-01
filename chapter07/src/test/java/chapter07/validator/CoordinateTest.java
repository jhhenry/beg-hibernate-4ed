package chapter07.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.Stream;

import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.autumncode.hibernate.util.SessionUtil;

import chapter07.validated.Coordinate;

public class CoordinateTest {
    private Coordinate persist(Coordinate entity) {
        try (Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
        }
        return entity;
    }

    
    static Stream<Arguments> validCoordinates() {
    	return Stream.of(
    			Arguments.of(1, 1),
    			Arguments.of(-1, 1),
    			Arguments.of(1, -1),
    			Arguments.of(0, 0)
    			);
    }

    @ParameterizedTest
    @MethodSource("validCoordinates")
    public void testValidCoordinate(Integer x, Integer y) {
        Coordinate c = Coordinate.builder().x(x).y(y).build();
        persist(c);
        // has passed validation, if we reach this point.
    }

    @Test
    public void testInvalidCoordinate() {
        assertThrows(ConstraintViolationException.class, () -> {
        	testValidCoordinate(-1, -1);
        	 fail("Should have gotten a constraint violation");
        });
       
    }
}
