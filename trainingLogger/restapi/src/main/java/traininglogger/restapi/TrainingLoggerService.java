package traininglogger.restapi;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public void addSessionToSessionLogger(Session session) {
    LOG.debug("addSessionToSessionLogger({})", session);
    this.sessionLogger.addSession(session);
  }






}