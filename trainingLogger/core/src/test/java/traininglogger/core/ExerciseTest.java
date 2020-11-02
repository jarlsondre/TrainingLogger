package traininglogger.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class ExerciseTest {

  @Test
  public void SetNameTest(){
    Exercise ex = new Exercise();
    ex.setName("Benk");
    assertEquals(ex.getName(), "Benk");
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