package traininglogger.restapi;

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




@Path(TrainingLoggerService.TRAINING_LOGGER_SERVICE_PATH)
public class TrainingLoggerService {

  public static final String TRAINING_LOGGER_SERVICE_PATH = "traininglogger";

  private static final Logger LOG = LoggerFactory.getLogger(TrainingLoggerService.class);

  @Inject
  private SessionLogger sessionLogger;

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
    return true;
  }

  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  public boolean deleteAll() {
    this.sessionLogger.deleteAll();
    return true;
  }







}