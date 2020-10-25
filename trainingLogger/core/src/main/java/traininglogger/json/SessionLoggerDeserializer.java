package traininglogger.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import traininglogger.core.Session;
import traininglogger.core.SessionLogger;

public class SessionLoggerDeserializer extends JsonDeserializer<SessionLogger> {

    private SessionDeserializer sessionDeserializer = new SessionDeserializer();

    /*
     * format: { "sessions" :[...] }
     */

    @Override
    public SessionLogger deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    public SessionLogger deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            JsonNode sessionsNode = objectNode.get("sessions");
            if (sessionsNode instanceof ArrayNode) {
                ArrayNode sessions = (ArrayNode) sessionsNode;
                SessionLogger sessionLogger = new SessionLogger();
                for (JsonNode element : sessions) {
                    Session session = this.sessionDeserializer.deserialize(element);
                    if (session != null) {
                        sessionLogger.addSession(session);
                    }
                }
                return sessionLogger;
            }
        }
        return null;
    }
}