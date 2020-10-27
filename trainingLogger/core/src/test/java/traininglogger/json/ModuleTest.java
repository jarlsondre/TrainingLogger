package traininglogger.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import traininglogger.core.Exercise;
import traininglogger.core.Set;

public class ModuleTest {

  private static ObjectMapper mapper;

  @BeforeAll
  public static void setUp() {
    mapper = new ObjectMapper();
    mapper.registerModule(new TrainingLoggerModule());
  }

  @Test
  public void testSetSerializer() {
    Set set = new Set(5, 100);
    try {
      String howStringShouldLookLike = "{\"repetitions\":5,\"weight\":100.0}";
      String stringProducedBySerializer = mapper.writeValueAsString(set);
      assertEquals(howStringShouldLookLike, stringProducedBySerializer);

    } catch (JsonProcessingException e) {
      fail();
    }
  }

  @Test
  public void testSetDeserializer() {
    String setAsJsonString = "{\"repetitions\":5,\"weight\":100.0}";
    try {
      Set setFromJsonString = mapper.readValue(setAsJsonString, Set.class);
      Set set = new Set(5, 100);
      assertEquals(set, setFromJsonString);
    } catch (JsonProcessingException e) {
      fail();
    }
  }

  @Test
  public void testExerciseSerializer() {
    Exercise exercise = new Exercise("Benk");
    Set set1 = new Set(5, 100);
    Set set2 = new Set(10, 200);
    exercise.addSets(set1, set2);
    try {
      String howStringShouldLookLike = "{\"name\":\"Benk\",\"sets\":[{\"repetitions\":5,\"weight\":100.0},{\"repetitions\":10,\"weight\":200.0}]}";
      String stringProducedBySerializer = mapper.writeValueAsString(exercise);
      assertEquals(howStringShouldLookLike, stringProducedBySerializer);

    } catch (JsonProcessingException e) {
      fail();
    }
  }

  @Test
  public void testExerciseDeserializer() {
    String exerciseAsJsonString = "{\"name\":\"Benk\",\"sets\":[{\"repetitions\":5,\"weight\":100.0},{\"repetitions\":10,\"weight\":200.0}]}";
    try {
      Exercise exerciseFromJsonString = mapper.readValue(exerciseAsJsonString, Exercise.class);
      Exercise exercise = new Exercise("Benk");
      Set set1 = new Set(5, 100);
      Set set2 = new Set(10, 200);
      exercise.addSets(set1, set2);
      assertEquals(exercise, exerciseFromJsonString);

    } catch (JsonProcessingException e) {
      fail();
    }
  }

}