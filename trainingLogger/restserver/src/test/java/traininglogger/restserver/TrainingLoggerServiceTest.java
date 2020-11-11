package traininglogger.restserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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

  private static String testPath = "user-data.json";

  protected boolean shouldLog() {
    return false;
  }

  @Override
  protected ResourceConfig configure() {
    final TrainingLoggerConfig config = new TrainingLoggerConfig();
    if (shouldLog()) { // TODO: Hva er vitsen når shouldLog() returnerer false? Sjekk om Hallvard
                       // endrer dette!
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

  // TODO: Potensielt "farlig" test. Antar at det er sessionloggeren i default-sessionlogger.json som returneres. Hvis man bruker appen
  // og lagrer en annen sessionlogger så vil denne i stedet returneres og testen feile. 
  @Test
  public void testGet_sessionLogger() {
    Response getResponse = target(TrainingLoggerService.TRAINING_LOGGER_SERVICE_PATH)
        .request(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8").get();
    assertEquals(200, getResponse.getStatus());
    try {
      SessionLogger sessionLogger = objectMapper.readValue(getResponse.readEntity(String.class), SessionLogger.class);
      Iterator<Session> it = sessionLogger.iterator();
      assertTrue(it.hasNext());
      // TODO: Test litt mer. Det er sessionloggeren som ligger i default-sessionlogger.json som er returnert.
    } catch (JsonProcessingException e) {
      fail(e.getMessage());
    }
  }

  // TODO: Endre hackete løsning?
  // Problemet er at deleteAll() skriver det som er tenkt å være brukerens data til fil. Skulle helst ha 
  // kunnet styre at det under testing lagres i en annen fil, slik at man ikke risikerer å skrive over 
  // faktiske brukerdata.
  // Prøvde å utvide TrainingLoggerConfig og TrainingLoggerService slik at man kunne styre hvilket filnavn 
  // saveSessionLogger() lagret under, men feila...
  // Løser nå problemet ved å slette den genererte fila etter testen.
  @Test
  public void testDelete_deleteAll() {
    Response getResponse = target(TrainingLoggerService.TRAINING_LOGGER_SERVICE_PATH)
        .request(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8").delete();
    assertEquals(200, getResponse.getStatus());
    try {
      Boolean deleted = objectMapper.readValue(getResponse.readEntity(String.class), Boolean.class);
      assertTrue(deleted);
      Path path = Paths.get(System.getProperty("user.home"), testPath);
      File testGeneratedFile = new File(path.toString());
      testGeneratedFile.delete();
    } catch (JsonProcessingException e) {
      fail(e.getMessage());
    }
  }

  // TODO: Skulle gjerne testa PUT også, men Hallvard har ikke noe eksempel og jeg prioriterer å ikke bruke 
  // mer tid på å prøve og finne ut av det selv.
}