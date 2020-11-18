package traininglogger.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

public class SetTest {

  @Test
  public void constructorTest() {
    try {
      new Set(1001, 100);
      fail("Det skal ikke være lov å lage et sett med flere enn 1000 repetisjoner");
    } catch (Exception e) {
      // Gjør ingenting
    }
    try {
      new Set(8, 1001);
      fail("Det skal ikke være lov å lage et sett med mer enn 1000kg");
    } catch (Exception e) {
      // GJør ingenting
    }
    try {
      new Set(1000, 1000);
    } catch (Exception e) {
      fail("Det skal være lov å lage et sett med 1000 repetisjoner og 1000kg");
    }
    try {
      new Set(10, 100);
    } catch (Exception e) {
      fail("Det skal være lov å lage et sett med 10 repetisjoner og 100kg");
    }
  }


}
