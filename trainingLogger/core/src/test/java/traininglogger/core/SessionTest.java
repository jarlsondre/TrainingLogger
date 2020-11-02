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

  private void sameDescriptionButDifferentDatesAreNotEqualTest() {
    Session session1 = new Session("Dette er en økt.");
    Session session2 = new Session("Dette er en økt.");
    session1.setDate("17/09/2020 14:35");
    session1.setDate("18/09/2020 14:35");
    assertNotEquals(session1, session2);
  }

  private void differentObjectsAreNotEqualTest() {
    Session session1 = new Session("Dette er en økt.");
    Session session2 = new Session("Dette er en ANNEN økt.");
    assertNotEquals(session1, session2);
    Session session3 = new Session("Dette går bra!");
    Session session4 = new Session("Dette går bra!");
    session3.setDate("17/09/2020 14:35");
    session4.setDate("18/09/2020 14:35");
    assertNotEquals(session3, session4);
  }

  @Test
  public void equalsTest(){
    Exercise exercise1 = new Exercise("Benkpress", new Set(2,2), new Set(4,4));
    Exercise exercise2 = new Exercise("Benkpress", new Set(2,2), new Set(4,4));
    Session session1 = new Session("braa!", exercise1, exercise2);
    Session session2 = new Session("braa!", exercise1, exercise2);
    assertEquals(session1, session2);
    this.differentObjectsAreNotEqualTest(); 
    this.sameDescriptionButDifferentDatesAreNotEqualTest();
  }

  @Test
  public void dateTest(){
    Session session = new Session();
    session.setDate("08/10/2020 17:29");
    //assertEquals("08/10/2020 17:29", session.getDateAsString());
    assertEquals(LocalDateTime.parse("08/10/2020 17:29", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), session.getDate());
  }

  @Test 
  public void iteratorTest(){
    Exercise exercise1 = new Exercise("Benkpress", new Set(2,2), new Set(4,4));
    Exercise exercise2 = new Exercise("Benkpress", new Set(2,2), new Set(4,4));
    Exercise exercise3 = new Exercise("Knebøy", new Set(2,2), new Set(4,4));
    Session session1 = new Session("braa!", exercise1, exercise2, exercise3);
    Iterator<Exercise> it = session1.iterator(); 
    assertTrue(it.hasNext());
    assertEquals(exercise1, it.next());
    assertTrue(it.hasNext());
    assertEquals(exercise2, it.next());
    assertTrue(it.hasNext());
    assertEquals(exercise3, it.next());
    assertFalse(it.hasNext());
  }

}
