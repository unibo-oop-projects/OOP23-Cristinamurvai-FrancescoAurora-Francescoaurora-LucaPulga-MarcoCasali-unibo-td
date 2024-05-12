package it.unibo.model.map.tile;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * Test {@link Tile}.
 */
public class TestTileFactory {
    private static final String JSON_MISSING_FEATURES = "{ sprite: \"1\"}";

    @Test
    void testTileNoFeatures() {
        final TileFactory factory = new TileFactoryImpl();
        assertEquals(factory.fromJSON(JSON_MISSING_FEATURES).getTileFeatures(), Set.of());
    }
}
