package traininglogger.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SetTest {

    @Test
    public void equalsTest() {
        Set set1 = new Set(5, 90);
        Set set2 = new Set(5,90);
        assertEquals(set1, set2);
        String notASet = "Jeg er ikke et Set";
        assertNotEquals(set1, notASet);
        Set set3 = new Set(10,10);
        assertNotEquals(set1,set3);
        Set set4 = new Set(5, 91);
        assertNotEquals(set1, set4);
    }

    @Test
    public void constructorTest() {
        Set set = new Set(5, 70);
        assertEquals(set.getRepetitions(), 5);
        assertEquals(set.getWeight(), 70);
    }

    @Test
    public void testToString(){
      Set set = new Set(5, 100);
      String setAsString = "repetitions: 5, weight: 100.0";
      assertEquals(set.toString(), setAsString);
    }
}
