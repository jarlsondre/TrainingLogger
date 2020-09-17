package trainingLogger.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class SessionTest {

    @Test
    public void testGetDescription_returnsNullInitially(){
        Session session = new Session();
        assertEquals(null, session.getDescription());
    }

    @Test
    public void testEquals_SameDescriptionButDifferentDatesAreNotEqual(){
        Session session1 = new Session("Dette er en økt.");
        Session session2 = new Session("Dette er en økt.");
        assertFalse(session1.equals(session2));
    }
}
