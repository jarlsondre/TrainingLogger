package traininglogger.core;

import java.util.Iterator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExerciseTest {

  @Test
  public void SetNameTest() {
    Exercise ex = new Exercise();
    try {
      ex.setName("benkpress1");  // Prøver å sette et navn som inneholder tall
      fail("Feilet ikke for tall i navnet.");
    } catch (IllegalArgumentException e) {
      // Do nothing
    }
    try {
      ex.setName("benk!press");  // Prøver å sette et navn som inneholder spesialtegn
      fail("Feilet ikke for spesialtegn i navnet.");
    } catch (IllegalArgumentException e) {
      // Do nothing
    }
    try {
      ex.setName("EtNavnSomErAltForLangtSlikAtTestenKanskjeFeiler");  // Prøver å sette et navn på over 20 bokstaver
      fail("Feilet ikke når navnet var på over 20 bokstaver.");
    } catch (IllegalArgumentException e) {
      // Do nothing
    }
    try {
      ex.setName("Benkpress");  // Prøver å sette et gyldig navn
    } catch (IllegalArgumentException e) {
      fail("Fikk ikke lov til å sette gyldig navn.");
    }
  }

  //Tester at settene kommer i korrekt rekkefølge og at iteratoren fungerer som den skal.
  @Test
  public void iteratorTest() {
    Set set1 = new Set(5, 70.0);
    Set set2 = new Set(6, 80.0);
    Set set3 = new Set(7, 90.0);
    Exercise exercise = new Exercise("Benkpress", set1, set2, set3);
    Iterator<Set> it = exercise.iterator();
    assertTrue(it.hasNext());
    assertTrue(ComparisonHelper.equalSet(it.next(), set1));
    assertTrue(it.hasNext());
    assertTrue(ComparisonHelper.equalSet(it.next(), set2));
    assertTrue(it.hasNext());
    assertTrue(ComparisonHelper.equalSet(it.next(), set3));
    assertFalse(it.hasNext());
  }

}