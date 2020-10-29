package traininglogger.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import traininglogger.core.SessionLogger;

public class TrainingLoggerPersistence {

  private ObjectMapper mapper;

  public TrainingLoggerPersistence() {
    mapper = new ObjectMapper();
    mapper.registerModule(new TrainingLoggerModule());
  }

  public SessionLogger readSessionLogger(Reader reader) throws IOException {
    return mapper.readValue(reader, SessionLogger.class);
  }

  public void writeSessionLogger(SessionLogger sessionLogger, Writer writer) throws IOException {
    mapper.writerWithDefaultPrettyPrinter().writeValue(writer, sessionLogger);
  }
}
