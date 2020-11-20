package traininglogger.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import traininglogger.core.Session;
import traininglogger.core.SessionLogger;

public class SessionLoggerDeserializer extends JsonDeserializer<SessionLogger> {

  private SessionDeserializer sessionDeserializer = new SessionDeserializer();

  /*
   * format: { "sessions" :[...] }
   */

  @Override
  public SessionLogger deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    return deserialize((JsonNode) treeNode);
  }

  /**
   * Konstruerer et SessionLogger-objekt fra en parset JsonNode.
   *
   * @param jsonNode templatet for nytt SessionLogger-objekt
   * @return det rekonstruerte SessionLogger-objektet hvis deserialiseringen lykkes, null ellers.
   */
  public SessionLogger deserialize(JsonNode jsonNode) {
    Map<String, Double> records;
    List<Session> sessionList = new ArrayList<>();

    if (jsonNode instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) jsonNode;
      JsonNode sessionsNode = objectNode.get("sessions");
      if (sessionsNode instanceof ArrayNode) {
        ArrayNode sessions = (ArrayNode) sessionsNode;
        for (JsonNode element : sessions) {
          Session session = this.sessionDeserializer.deserialize(element);
          if (session != null) {
            sessionList.add(session);
          }
        }
        JsonNode recordsNode = objectNode.get("records");
        ObjectMapper mapper = new ObjectMapper();
        records = mapper.convertValue(recordsNode, new TypeReference<>() {
        });
        return new SessionLogger(sessionList, records);
      }
    }
    return null;
  }
}