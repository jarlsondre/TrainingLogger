package trainingLogger.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import trainingLogger.core.Session;
import trainingLogger.core.SessionLogger;

public class SessionLoggerDeserializer extends JsonDeserializer<SessionLogger> {

    private SessionDeserializer deserializer = new SessionDeserializer();

    @Override
    public SessionLogger deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        TreeNode treenode = parser.getCodec().readTree(parser);
        if(treenode instanceof ObjectNode){
            ObjectNode node = (ObjectNode) treenode;
            JsonNode loggerNode = node.get("sessions");
            SessionLogger logger = new SessionLogger();
            if(loggerNode instanceof ArrayNode){
                ArrayNode arrayNode = (ArrayNode) loggerNode;
                for(JsonNode n : arrayNode){
                    Session session = this.deserializer.deserialize(n);
                    if(session != null){
                        logger.addSession(session);
                    }
                }
            }
            return logger;
        }
        return null;
    }

    /*
   * format: 
   * {"sessions": [ ... ]}
   */


    
}