package traininglogger.restserver;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import traininglogger.core.SessionLogger;
import traininglogger.json.TrainingLoggerPersistence;
import traininglogger.restapi.TrainingLoggerService;

public class TrainingLoggerConfig extends ResourceConfig {

  private SessionLogger sessionLogger;
  private String pathToSaveTo;
  private static String userData = "user-data.json";

  /**
   * Initialize this TrainingLoggerConfig.
   *
   * @param sessionLogger sessionLogger instance to serve
   */
  public TrainingLoggerConfig(SessionLogger sessionLogger, String path) {
    setSessionLogger(sessionLogger);
    setPathToSaveTo(path);
    register(TrainingLoggerService.class);
    register(TrainingLoggerModuleObjectMapperProvider.class);
    register(JacksonFeature.class);
    register(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(TrainingLoggerConfig.this.sessionLogger);
      }
    });
    register(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(TrainingLoggerConfig.this.pathToSaveTo);
      }
    });
  }

  /**
   * Initialize this TrainingLoggerConfig with an initial SessionLogger.
   */
  public TrainingLoggerConfig() {
    this(getInitialSessionLogger(userData), userData);
  }

  public TrainingLoggerConfig(String path) {
    this(getInitialSessionLogger(path), path);
  }

  public SessionLogger getSessionLogger() {
    return this.sessionLogger;
  }

  public void setSessionLogger(SessionLogger sessionLogger) {
    this.sessionLogger = sessionLogger;
  }

  public void setPathToSaveTo(String path) {
    this.pathToSaveTo = path;
  }

  private static SessionLogger getInitialSessionLogger(String path) {
    Reader reader = null;
    TrainingLoggerPersistence trainingLoggerPersistence = new TrainingLoggerPersistence();
    // Kjører vi i testsammenheng?
    if (!path.equals(userData)) {
      URL url = TrainingLoggerConfig.class.getResource(path);
      try {
        reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
      } catch (IOException e) {
        System.out.println("Kunne ikke lese restserver-test-sessionlogger.json. (" + e + ")");
      }
    } else {
      // Hvis ikke - let etter lagret brukerdata først:
      try {
        reader = new FileReader(Paths.get(System.getProperty("user.home"), userData).toFile(), StandardCharsets.UTF_8);
      } catch (IOException ioex) {
        System.err.println("Fant ingen " + userData + " på hjemmeområdet");
      }
      // Hvis man ikke fant noe, bruk medølgende eksempelfil:
      URL url = TrainingLoggerConfig.class.getResource("default-sessionlogger.json");
      if ((reader == null) && (url != null)) {
        try {
          reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
          System.out.println("Kunne ikke lese default-sessionlogger.json. (" + e + ")");
        }
      }
    }

    SessionLogger initialSessionLogger = null;
    if (reader != null) {
      try {
        initialSessionLogger = trainingLoggerPersistence.readSessionLogger(reader);
      } catch (IOException e) {
        System.out.println("Kunne ikke deserialisere fra Reader, så returnerer en helt ny SessionLogger (" + e + ")");
      } finally {
        try {
          reader.close();
        } catch (IOException e) {
          System.err.println("Klarte ikke å lukke reader" + "(" + e + ")");
        }
      }
    }

    if (initialSessionLogger == null) {
      initialSessionLogger = new SessionLogger();
    }
    return initialSessionLogger;
  }

}