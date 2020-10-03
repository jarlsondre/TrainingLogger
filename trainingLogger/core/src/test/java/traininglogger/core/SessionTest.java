package traininglogger.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;

public class SessionTest {


  @Test
  public void testConstructor(){
    Exercise e1 = new Exercise("Knebøy", 5,5,5,5,4,4);
    Exercise e2 = new Exercise("Benkpress", 5,5,5,5,4,3);
    Exercise e3 = new Exercise("Markløft", 5,5,5,5,4,2);
    Collection<Exercise> col = new ArrayList<>(Arrays.asList(e1,e2,e3));
    Session session = new Session("Bra!", e1,e2,e3);
    assertEquals(col, session.getListOfExercises());
    try {
      new Session("Flott!");
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testExerciseList(){
    Exercise e1 = new Exercise("Knebøy", 5,5,5,5,4,4);
    Exercise e2 = new Exercise("Benkpress", 5,5,5,5,4,3);
    Exercise e3 = new Exercise("Markløft", 5,5,5,5,4,2);
    Session session = new Session("Bra!", e1,e2);
    Collection<Exercise> col = session.getListOfExercises();
    col.add(e3);
    assertFalse(session.getListOfExercises().equals(col));
  }

  @Test
  public void testGetDescription_returnsNullInitially() {
    Session session = new Session();
    assertEquals(null, session.getDescription());
  }

  @Test
  public void testEquals_SameDescriptionButDifferentDatesAreNotEqual() {
    Session session1 = new Session("Dette er en økt.");
    Session session2 = new Session("Dette er en økt.");
    assertFalse(session1.equals(session2));
  }

  @Test
  public void testEquals_DifferentObjectsAreNotEqual() {
    Session session1 = new Session("Dette er en økt.");
    Session session2 = new Session("Dette er en ANNEN økt.");
    if (session1.equals(session2)) {
      fail();
    }
    Session session3 = new Session("Dette går bra!");
    Session session4 = new Session("Dette går bra!");
    session3.setDate("17/09/2020 14:35");
    session4.setDate("18/09/2020 14:35");
    if (session3.equals(session4)) {
      fail();
    }
  }

}
