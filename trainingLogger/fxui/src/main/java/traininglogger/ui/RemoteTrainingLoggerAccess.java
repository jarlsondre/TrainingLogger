package traininglogger.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;
import traininglogger.json.TrainingLoggerModule;


public class RemoteTrainingLoggerAccess implements TrainingLoggerAccess {

  private final URI endpointBaseUri;
  private final ObjectMapper objectMapper;
  private SessionLogger sessionLogger;

  /**
   * Konstruerer en ny RemoteTrainingLoggerAccess med gitt URI som endepunkt.
   *
   * @param endpointBaseUri URI som instansen henvender seg til i HTTP-forespørsler
   */

  public RemoteTrainingLoggerAccess(URI endpointBaseUri) {
    this.endpointBaseUri = endpointBaseUri;
    this.objectMapper = new ObjectMapper().registerModule(new TrainingLoggerModule());
    this.sessionLogger = getSessionLogger();
  }

  @Override
  public SessionLogger getSessionLogger() {
    if (this.sessionLogger == null) {
      HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
          .header("Accept", "application/json").GET().build();
      try {
        final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
            HttpResponse.BodyHandlers.ofString());
        final String responseString = response.body();
        this.sessionLogger = this.objectMapper.readValue(responseString, SessionLogger.class);
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    return this.sessionLogger;
  }

  @Override
  public void addSession(Session session) {
    try {
      String json = this.objectMapper.writeValueAsString(session);
      HttpRequest request = HttpRequest.newBuilder(this.endpointBaseUri)
          .header("Accept", "application/json")
          .header("Content-Type", "application/json").PUT(BodyPublishers.ofString(json)).build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      Boolean added = this.objectMapper.readValue(responseString, Boolean.class);
      if (added != null) {
        this.sessionLogger.addSession(session);
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void deleteAll() {
    try {
      HttpRequest request = HttpRequest.newBuilder(this.endpointBaseUri)
          .header("Accept", "application/json").DELETE()
          .build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      Boolean removed = objectMapper.readValue(responseString, Boolean.class);
      if (removed != null) {
        this.sessionLogger.deleteAll();
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}