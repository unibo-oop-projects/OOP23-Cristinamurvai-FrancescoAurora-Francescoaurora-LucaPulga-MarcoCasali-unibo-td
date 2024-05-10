package it.unibo.model.utilities;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class Vector2DDeserializer extends StdDeserializer<Vector2D> {

    public Vector2DDeserializer() {
        super(Vector2D.class);
    }

    @Override
    public Vector2D deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        if (jp.getCodec() == null) {
            return null;
        }
        JsonNode node = jp.getCodec().readTree(jp);
        int x = node.get("x").asInt();
        int y = node.get("y").asInt();
        return new Vector2D(x, y);
    }
}