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
    if (jnode instanceof ObjectNode) {
      Set set = new Set();
      ObjectNode node = (ObjectNode) jnode;
      JsonNode repsNode = node.get("reps");
      if (repsNode instanceof TextNode) {
        set.setReps(Integer.parseInt(((TextNode) repsNode).asText()));
      }
      JsonNode weightNode = node.get("weight");
      if (weightNode instanceof TextNode) {
        set.setWeight(Double.parseDouble(((TextNode) weightNode).asText()));
      }
      return set;
    }
    return null;
  }

}