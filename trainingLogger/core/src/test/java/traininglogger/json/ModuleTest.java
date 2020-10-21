package traininglogger.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import traininglogger.core.Exercise;
import traininglogger.core.Session;
import traininglogger.core.Set;

public class ModuleTest {

  private static ObjectMapper mapper;

  @BeforeAll
  public static void setUp() {
    ModuleTest.mapper = new ObjectMapper();
    mapper.registerModule(new TrainingLoggerModule());
  }

  @Test
  public void testSessionSerializer() {
    Exercise e1 = new Exercise("Knebøy", new Set(5,5), new Set(6,6));
    Exercise e2 = new Exercise("Knebøy", new Set(5,5), new Set(6,7));
    Exercise e3 = new Exercise("Knebøy", new Set(5,5), new Set(6,9));
    Session session = new Session("Det var en fin økt!", e1,e2,e3);
    session.setDate("15/09/2020 10:02");
    try {
      assertEquals(
        "{\"stringDescription\":\"Det var en fin økt!\",\"date\":\"15/09/2020 10:02\",\"exercises\":[{\"name\":\"Knebøy\",\"sets\":[{\"reps\":\"5\",\"weight\":\"5.0\"},{\"reps\":\"6\",\"weight\":\"6.0\"}]},{\"name\":\"Knebøy\",\"sets\":[{\"reps\":\"5\",\"weight\":\"5.0\"},{\"reps\":\"6\",\"weight\":\"7.0\"}]},{\"name\":\"Knebøy\",\"sets\":[{\"reps\":\"5\",\"weight\":\"5.0\"},{\"reps\":\"6\",\"weight\":\"9.0\"}]}]}",
          mapper.writeValueAsString(session));
    } catch (JsonProcessingException e) {
      fail();
    }
  }

  @Test
  public void testSessionDeserializer() {
    Session session = null;
    try {
      session = ModuleTest.mapper
          .readValue("{\"stringDescription\":\"Det var en fin økt!\",\"date\":\"15/09/2020 10:02\",\"exercises\":[{\"name\":\"Knebøy\",\"sets\":[{\"reps\":\"5\",\"weight\":\"5.0\"},{\"reps\":\"6\",\"weight\":\"6.0\"}]},{\"name\":\"Knebøy\",\"sets\":[{\"reps\":\"5\",\"weight\":\"5.0\"},{\"reps\":\"6\",\"weight\":\"7.0\"}]},{\"name\":\"Knebøy\",\"sets\":[{\"reps\":\"5\",\"weight\":\"5.0\"},{\"reps\":\"6\",\"weight\":\"9.0\"}]}]}", Session.class);
    } catch (JsonMappingException e) {
      fail();
    } catch (JsonProcessingException e) {
      fail();
    }
    Exercise e1 = new Exercise("Knebøy", new Set(5,5), new Set(6,6));
    Exercise e2 = new Exercise("Knebøy", new Set(5,5), new Set(6,7));
    Exercise e3 = new Exercise("Knebøy", new Set(5,5), new Set(6,9));
    Session session_test = new Session("Det var en fin økt!", e1,e2,e3);
    session_test.setDate("15/09/2020 10:02");
    assertEquals(session_test, session);
  }

  @Test
  public void testExerciseSerializer() {
    Exercise exercise = new Exercise("Knebøy", new Set(5,5), new Set(6,6));
    try {
      assertEquals("{\"name\":\"Knebøy\",\"sets\":[{\"reps\":\"5\",\"weight\":\"5.0\"},{\"reps\":\"6\",\"weight\":\"6.0\"}]}",
          mapper.writeValueAsString(exercise));
    } catch (JsonProcessingException e) {
      fail();
    }
  }

  @Test
  public void testExerciseDeserializer() {
    Exercise exercise = null;
    try {
      exercise = ModuleTest.mapper
          .readValue("{\"name\":\"Knebøy\",\"sets\":[{\"reps\":\"5\",\"weight\":\"5.0\"},{\"reps\":\"6\",\"weight\":\"6.0\"}]}", Exercise.class);
    } catch (JsonMappingException e) {
      e.printStackTrace();
      fail();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      fail();
    }
    Exercise test_exercise = new Exercise("Knebøy", new Set(5,5), new Set(6,6));
    assertEquals(test_exercise, exercise);
  }


}