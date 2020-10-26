package traininglogger.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import traininglogger.core.Set;

public class SetDeserializer extends JsonDeserializer<Set> {


  @Override
  public Set deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    TreeNode treenode = parser.getCodec().readTree(parser);
    return deserialize((JsonNode) treenode);
  }

  public Set deserialize(JsonNode jnode) {
    int reps = 0;
    double weight = 0;
    if (jnode instanceof ObjectNode) {
      ObjectNode node = (ObjectNode) jnode;
      JsonNode repsNode = node.get("repetitions");
      if (repsNode instanceof TextNode) {
        reps = (Integer.parseInt(((TextNode) repsNode).asText()));
      }
      JsonNode weightNode = node.get("weight");
      if (weightNode instanceof TextNode) {
        weight = (Double.parseDouble(((TextNode) weightNode).asText()));
      }
      return new Set(reps, weight);
    }
    return null;
  }

}