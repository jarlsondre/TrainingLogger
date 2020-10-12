package traininglogger.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import traininglogger.core.Exercise;
import traininglogger.core.Session;

public class FileHandlerTest {

  private static List<Session> sessionsList;

  private static String sessionsString = 
  "[{\"stringDescription\":\"Bra\",\"date\":\"02/10/2020 16:37\",\"exercises\":[{\"name\":\"Knebøy\",\"sets\":[5,5,6,6]},{\"name\":\"Knebøy\",\"sets\":[5,5,6,7]},{\"name\":\"Knebøy\",\"sets\":[5,5,6,9]}]},{\"stringDescription\":\"Strålende\",\"date\":\"02/10/2020 16:37\",\"exercises\":[]}]";

  @BeforeAll
  public static void setUp() {
    Exercise e1 = new Exercise("Knebøy", 5, 5, 6, 6);
    Exercise e2 = new Exercise("Knebøy", 5, 5, 6, 7);
    Exercise e3 = new Exercise("Knebøy", 5, 5, 6, 9);
    Session s1 = new Session("Bra");
    Session s2 = new Session("Strålende");
    s1.setDate("02/10/2020 16:37");
    s2.setDate("02/10/2020 16:37");
    s1.addExercises(e1, e2, e3);
    sessionsList = new ArrayList<>();
    sessionsList.add(s1);
    sessionsList.add(s2);
  }

  @Test
  public void testReadFromFile() {
    ByteArrayInputStream in = new ByteArrayInputStream(sessionsString.getBytes());
    List<Session> s = null;
    try {
      s = FileHandler.readFromFile(in);
    } catch (IOException e) {
      fail();
    }
    assertEquals(sessionsList, s);
  }

  @Test
  public void testWriteToFile() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {
      FileHandler.writeToFile(out, sessionsList);
    } catch (IOException e) {
      fail();
    }
    assertEquals(sessionsString, new String(out.toByteArray()));
  }
}