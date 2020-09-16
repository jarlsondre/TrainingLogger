package trainingLogger.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SessionTest {

    @Test
    public void testGetDescription_returnsNullInitially(){
        Session session = new Session();
        assertEquals(null, session.getDescription());
    }
}
