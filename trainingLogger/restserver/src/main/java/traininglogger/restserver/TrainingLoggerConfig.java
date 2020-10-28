package traininglogger.restserver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;
import traininglogger.json.TrainingLoggerPersistence;


public class TrainingLoggerConfig extends ResourceConfig {

  private SessionLogger sessionLogger;


  /**
   * Initialize this TodoConfig.
   *
   * @param sessionLogger sessionLogger instance to serve
   */
  public TrainingLoggerConfig(SessionLogger sessionLogger) {
    setSessionLogger(sessionLogger);
    register(TrainingLoggerService.class);
    register(TrainingLoggerModuleObjectMapperProvider.class);
    register(JacksonFeature.class);
    register(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(TrainingLoggerConfig.this.sessionLogger);
      }
    });
  }

  /**
   * Initialize this TrainingLoggerConfig with a default SessionLogger.
   */
  public TrainingLoggerConfig() {
    this(createDefaultSessionLogger());
  }

  public SessionLogger getSessionLogger() {
    return this.sessionLogger;
  }

  public void setSessionLogger(SessionLogger sessionLogger) {
    this.sessionLogger = sessionLogger;
  }

  private static SessionLogger createDefaultSessionLogger() {
    TrainingLoggerPersistence trainingLoggerPersistence = new TrainingLoggerPersistence();
    URL url = TrainingLoggerConfig.class.getResource("default-sessionlogger.json");
    if (url != null) {
      try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
        return trainingLoggerPersistence.readSessionLogger(reader);
      } catch (IOException e) {
        System.out.println("Couldn't read default-sessionlogger.json, so rigging SessionLogger manually ("
            + e + ")");
      }
    }

    SessionLogger sessionLogger = new SessionLogger();
    Session session = new Session();
    sessionLogger.addSession(session);
    return sessionLogger;
  }

  
  
}