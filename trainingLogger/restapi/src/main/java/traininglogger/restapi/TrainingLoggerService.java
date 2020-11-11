package traininglogger.restapi;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;
import traininglogger.json.TrainingLoggerPersistence;

@Path(TrainingLoggerService.TRAINING_LOGGER_SERVICE_PATH)
public class TrainingLoggerService {

  public static final String TRAINING_LOGGER_SERVICE_PATH = "traininglogger";

  private static final Logger LOG = LoggerFactory.getLogger(TrainingLoggerService.class);

  @Inject
  private SessionLogger sessionLogger;
  private TrainingLoggerPersistence trainingLoggerPersistence = null;

  @Inject
  private String pathToSaveTo;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public SessionLogger getSessionLogger() {
    return this.sessionLogger;
  }

  @PUT // Burde denne være POST?
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public boolean addSession(Session session) {
    LOG.debug("addSession({})", session);
    this.sessionLogger.addSession(session);
    saveSessionLogger();
    return true;
  }

  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  public boolean deleteAll() {
    this.sessionLogger.deleteAll();
    saveSessionLogger();
    return true;
  }

  private void saveSessionLogger() {
    if (trainingLoggerPersistence == null) {
      trainingLoggerPersistence = new TrainingLoggerPersistence();
    }
    java.nio.file.Path path = Paths.get(System.getProperty("user.home"), pathToSaveTo);
    try (Writer writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8)) {
      trainingLoggerPersistence.writeSessionLogger(this.sessionLogger, writer);
    } catch (IOException e) {
      System.err.println("Fikk ikke skrevet til " + pathToSaveTo + " på hjemmeområdet");
    }
  }
}