package traininglogger.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

public class SetTest {

    @Test
    public void constructorTest() {
        Set set = new Set(5, 70);
        assertEquals(set.getRepetitions(), 5);
        assertEquals(set.getWeight(), 70);
    }


}
