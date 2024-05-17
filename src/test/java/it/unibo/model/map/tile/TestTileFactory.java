package it.unibo.model.map.tile;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * Test {@link TileFactory}.
 */
public class TestTileFactory {
    private static final String JSON_MISSING_FEATURES = "{ sprite: \"1\"}";
    private static final String JSON_OK = "{ sprite: \"defense.png\", features: [\"DEFENSE\"]}";
    private final TileFactory factory = new TileFactoryImpl();

    @Test
    void testTileNoFeatures() {
        assertEquals(factory.fromJSON(JSON_MISSING_FEATURES).getTileFeatures(), Set.of());
    }

    @Test
    void testTileNormal() {
        final Tile t = factory.fromJSON(JSON_OK);
        assertEquals(t.getTileFeatures(), Set.of(TileFeature.DEFENSE));
        assertEquals(t.getSprite(), "defense.png");
    }
}
