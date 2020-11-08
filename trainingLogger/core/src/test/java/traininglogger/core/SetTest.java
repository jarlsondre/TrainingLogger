package traininglogger.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

public class SetTest {

    @Test
    public void constructorTest() {
        try {
            Set set1 = new Set(1001, 100);
            fail("Det skal ikke være lov å lage et sett med flere enn 1000 repetisjoner");
        }
        catch (Exception e) {
            // Gjør ingenting
        }
        try {
            Set set2 = new Set(8, 1001);
            fail("Det skal ikke være lov å lage et sett med mer enn 1000kg");
        }
        catch (Exception e) {
            // GJør ingenting
        }
        try {
            Set set3 = new Set(1000, 1000);
        }
        catch (Exception e) {
            fail("Det skal være lov å lage et sett med 1000 repetisjoner og 1000kg");
        }
        try {
            Set set4 = new Set(10, 100);
        }
        catch (Exception e) {
            fail("Det skal være lov å lage et sett med 10 repetisjoner og 100kg");
        }
    }


}
