package traininglogger.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ExerciseTest {

  @Test
  public void constructorTest(){
    Set set1 = new Set(5,5.0);
    Set set2 = new Set(6,6.0);
    Set set3 = new Set(7,7.0);
    List<Set> sets = new ArrayList<>();
    Exercise exercise = new Exercise("Knebøy", set1, set2, set3);
    Iterator<Set> it = exercise.iterator();
    assertTrue(it.hasNext());
    assertEquals(it.next(), set1);
    assertTrue(it.hasNext());
    assertEquals(it.next(), set2);
    assertTrue(it.hasNext());
    assertEquals(it.next(), set3);
    assertFalse(it.hasNext());
    assertTrue(exercise.getName().equals("Knebøy"));
    try {
      new Exercise("Knebøy");
    } catch (Exception e) {
      fail();
    }
  } 
  
  @Test
  public void addSetsTest(){
    Exercise exercise = new Exercise("Knebøy", new Set(5, 5.0));
    exercise.addSets(new Set(2,2), new Set(3,3));
    Collection<Set> col = new ArrayList<>();
    Set set1 = new Set(5,5.0);
    Set set2 = new Set(2,2);
    Set set3 = new Set(3,3);
    col.add(set1);
    col.add(set2);
    col.add(set3);
    assertEquals(col, exercise.getSets());
    assertEquals(new ArrayList<>(), new Exercise("Knebøy").getSets());
  }

  @Test
  public void setsListEncapsulationTest(){
   Exercise exercise = new Exercise("Knebøy", new Set(1,1));
   Collection<Set> col = exercise.getSets(); 
   Set s = new Set(2,2);
   col.add(s); 
   assertFalse(col.equals(exercise.getSets()));
  }

  @Test
  public void equalsMethodTest(){
    Exercise exercise1 = new Exercise("Benkpress", new Set(1,1), new Set(1,1), new Set(1,1));
    Exercise exercise2 = new Exercise("Benkpress", new Set(1,1), new Set(1,1), new Set(1,1));
    assertEquals(exercise1, exercise2);
    exercise2.addSets(new Set(2,2));
    assertFalse(exercise1.equals(exercise2));
  }



}