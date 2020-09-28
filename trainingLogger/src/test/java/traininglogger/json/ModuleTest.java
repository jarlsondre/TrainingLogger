package traininglogger.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import traininglogger.core.Session;

public class ModuleTest {

  private static ObjectMapper mapper;

  @BeforeAll
  public static void setUp() {
    ModuleTest.mapper = new ObjectMapper();
    mapper.registerModule(new TrainingLoggerModule());
  }

  @Test
  public void testSessionSerializer() {
    Session session = new Session("det var en fin økt");
    session.setDate("15/09/2020 10:02");
    try {
      assertEquals("{\"stringDescription\":\"det var en fin økt\",\"date\":\"15/09/2020 10:02\"}",
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
          .readValue("{\"stringDescription\":\"det var en fin økt\",\"date\":\"15/09/2020 10:02\"}", Session.class);
    } catch (JsonMappingException e) {
      fail();
    } catch (JsonProcessingException e) {
      fail();
    }
    Session test_Session = new Session("det var en fin økt");
    test_Session.setDate("15/09/2020 10:02");
    assertEquals(test_Session, session);
  }

}