package traininglogger.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SetTest {

    @Test
    public void equalsTest() {
        Set set1 = new Set(5, 90);
        Set set2 = new Set(5,90);
        assertEquals(set1, set2);
    }

    @Test
    public void constructorTest() {
        Set set = new Set(5, 70);
        assertEquals(set.getRepetitions(), 5);
        assertEquals(set.getWeight(), 70);
    }
}
