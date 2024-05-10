package it.unibo.model.utilities;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class Position2DDeserializer extends StdDeserializer<Position2D> {

    public Position2DDeserializer() {
        super(Position2D.class);
    }

    @Override
    public Position2D deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        if (jp.getCodec() == null) {
            return null;
        }
        JsonNode node = jp.getCodec().readTree(jp);
        int x = node.get("x").asInt();
        int y = node.get("y").asInt();
        return new Position2D(x, y);
    }
}
