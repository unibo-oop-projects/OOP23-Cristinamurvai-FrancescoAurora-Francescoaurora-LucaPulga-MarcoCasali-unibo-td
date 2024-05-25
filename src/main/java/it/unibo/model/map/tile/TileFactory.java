package it.unibo.model.map.tile;

import java.io.IOException;

/**
 * Interface for a factory of {@link Tile}s.
 */
public interface TileFactory {
    /**
     * @param fileName The file name
     * @return A {@link Tile} created from a JSON file
     * @throws IOException if an I/O error occurs
     */
    Tile fromJSONFile(String fileName) throws IOException;

    /**
     * @param jsonString The source string in JSON format
     * @return A {@link Tile} created from a JSON string
     */
    Tile fromJSON(String jsonString);

    /**
     * @param name The name of the {@link Tile} as defined in the resources
     * @return A {@link Tile} created from the available resources
     */
    Tile fromName(String name);
}
