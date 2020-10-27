package traininglogger.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class ExerciseTest {

  @Test
  public void testEquals(){
    Set set1 = new Set(5, 100);
    Set set2 = new Set(6, 200);
    Set set3 = new Set(7, 300);
    Exercise ex1 = new Exercise("Benk", set1, set2, set3);
    Exercise ex2 = new Exercise("Benk", set1, set2, set3);
    assertEquals(ex1, ex2);
    Exercise ex3 = new Exercise("Benk", set3, set2, set1);
    assertNotEquals(ex1, ex3);
    String iAmNotAnExercise = "Hallo";
    assertNotEquals(ex1, iAmNotAnExercise);
    Exercise ex4 = new Exercise("Bøy", set1, set2, set3);
    assertNotEquals(ex2, ex4);
  }

  @Test
  public void testSetName(){
    Exercise ex = new Exercise();
    ex.setName("Benk");
    assertEquals(ex.getName(), "Benk");
  }
}