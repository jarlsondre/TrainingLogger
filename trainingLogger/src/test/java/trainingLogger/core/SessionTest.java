package trainingLogger.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class SessionTest {

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
    System.out.println(session1.getDateString());
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
