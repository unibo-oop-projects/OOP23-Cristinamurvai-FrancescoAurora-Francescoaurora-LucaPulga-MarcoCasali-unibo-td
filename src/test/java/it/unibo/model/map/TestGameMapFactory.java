package it.unibo.model.map;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Test {@link GameMapFactory}.
 */
class TestGameMapFactory {

    private static final String TEST_MAP = """
            {
                "rows": 20,
                "columns": 20,
                "filler": "grass",
                "tiles": [
            {
                "tile": "path_start",
                "positions": ["40"]
            },
            {
                "tile": "path_end",
                "positions": ["379"]
            },
            {
                "tile": "path_right",
                "positions": ["41-56","364-378"]
            },
            {
                "tile" : "path_down",
                "positions": ["57/237","244/344"]
            },
            {
                "tile" : "path_left",
                "positions": ["245-257"]
            },
            {
                "tile" : "buildslot",
                "positions": ["23","67","134","178","212","241","287","355","389"]
            }
        ]
    } """;
    private static final int TEST_MAP_ROWS = 20;
    private static final int TEST_MAP_COLUMNS = 20;

    @Test
    void testSimple() throws IOException {
        final GameMapFactory factory = new GameMapFactoryImpl();
        final GameMap map = factory.fromJSON(TEST_MAP);
        assertEquals(map.getRows(), TEST_MAP_ROWS);
        assertEquals(map.getColumns(), TEST_MAP_COLUMNS);
        assertEquals(map.getTiles().count(), TEST_MAP_COLUMNS * TEST_MAP_ROWS);
    }
}
