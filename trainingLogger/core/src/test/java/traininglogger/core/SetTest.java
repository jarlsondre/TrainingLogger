package traininglogger.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SetTest {

    @Test
    public void equalsTest() {
        Set set1 = new Set(5, 90);
        Set set2 = new Set(5, 90);
        assertEquals(set1, set2);
        Set set3 = new Set(5, 90);
        Set set4 = new Set(4, 90);
        assertNotEquals(set3, set4);
    }

    @Test
    public void constructorTest() {
        Set set1 = new Set();
        Set set2 = new Set(5, 70);
        set1.setReps(5);
        set1.setWeight(70);
        assertEquals(set1, set2);
    }
}
