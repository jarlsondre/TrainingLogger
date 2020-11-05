package traininglogger.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import traininglogger.core.Set;

public class SetDeserializer extends JsonDeserializer<Set> {

  @Override
  public Set deserialize(JsonParser parser, DeserializationContext ctxt) 
      throws IOException, JsonProcessingException {
    TreeNode treenode = parser.getCodec().readTree(parser);
    return deserialize((JsonNode) treenode);
  }

  /**
   * Konstruerer et Set-objekt fra en parset JsonNode.
   *
   * @param jsonNode templatet for nytt Set-objekt
   * @return det rekonstruerte Set-objektet hvis deserialiseringen lykkes, null ellers.
   */
  public Set deserialize(JsonNode jsonNode) {
    if (jsonNode instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) jsonNode;
      JsonNode weightNode = objectNode.get("weight");
      JsonNode repetitionsNode = objectNode.get("repetitions");
      if (weightNode instanceof DoubleNode && repetitionsNode instanceof IntNode) {
        double weight = ((DoubleNode) weightNode).asDouble();
        int repetitions = ((IntNode) repetitionsNode).asInt();
        Set set = new Set(repetitions, weight);
        return set;
      }
    }
    return null;
  }
}
