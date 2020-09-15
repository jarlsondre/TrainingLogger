package trainingLogger.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import trainingLogger.core.Session;

public class SessionDeserializer extends JsonDeserializer<Session> {

    @Override
    public Session deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        TreeNode treenode = parser.getCodec().readTree(parser);
        if (treenode instanceof ObjectNode) {
            ObjectNode node = (ObjectNode) treenode;
            Session session = new Session();
            JsonNode textNode1 = node.get("stringDescription");
            if (textNode1 instanceof TextNode) {
                session.setDescription(((TextNode) textNode1).asText());
            }
            JsonNode textNode2 = node.get("date");
            if (textNode2 instanceof TextNode) {
                session.setDescription(((TextNode) textNode2).asText());
            }
            return session;
        }
        return null;
    }

}