package traininglogger.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class SessionTest {

  @Test
  public void dateTest(){
    Session session = new Session();
    session.setDate("08/10/2020 17:29");
    assertEquals(LocalDateTime.parse("08/10/2020 17:29", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), session.getDate());
  }


  /*public void iteratorTest(){
    Exercise exercise1 = new Exercise("Benkpress", new Set(2,2), new Set(4,4));
    Exercise exercise2 = new Exercise("Benkpress", new Set(2,2), new Set(4,4));
    Exercise exercise3 = new Exercise("Knebøy", new Set(2,2), new Set(4,4));
    Session session1 = new Session("braa!", exercise1, exercise2, exercise3);
    Iterator<Exercise> it = session1.iterator(); 
    assertTrue(it.hasNext());
    assertEquals(ComparisonHelper.equalExercise(it.next(), exercise1));
    assertTrue(it.hasNext());
    assertEquals(ComparisonHelper.equalExercise(it.next(), exercise2));
    assertTrue(it.hasNext());
    assertEquals(ComparisonHelper.equalExercise(it.next(), exercise3));
    assertFalse(it.hasNext());
  }*/


}
