package traininglogger.restserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.Iterator;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import traininglogger.core.Session;
import traininglogger.core.SessionLogger;
import traininglogger.restapi.TrainingLoggerService;

public class TrainingLoggerServiceTest extends JerseyTest {

  protected boolean shouldLog() {
    return false;
  }

  @Override
  protected ResourceConfig configure() {
    final TrainingLoggerConfig config = new TrainingLoggerConfig();
    if (shouldLog()) {
      enable(TestProperties.LOG_TRAFFIC);
      enable(TestProperties.DUMP_ENTITY);
      config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, "WARNING");
    }
    return config;
  }

   @Override
  protected TestContainerFactory getTestContainerFactory() throws TestContainerException {
    return new GrizzlyTestContainerFactory();
  }

  private ObjectMapper objectMapper;


  @Override
  @BeforeEach
  public void setUp() throws Exception {
    super.setUp();
    objectMapper = new TrainingLoggerModuleObjectMapperProvider().getContext(getClass());
  }

  @AfterEach
  public void tearDown() throws Exception {
    super.tearDown();
  }

  @Test
  public void testGet_sessionLogger() {
    Response getResponse = target(TrainingLoggerService.TRAINING_LOGGER_SERVICE_PATH)
        .request(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
        .get();
    assertEquals(200, getResponse.getStatus());
    try {
      SessionLogger sessionLogger = objectMapper.readValue(getResponse.readEntity(String.class), SessionLogger.class);
      Iterator<Session> it = sessionLogger.iterator();
      assertTrue(it.hasNext());
    } catch (JsonProcessingException e) {
      fail(e.getMessage());
    }
  }

}