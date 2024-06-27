package it.unibo.model.map;

import java.io.IOException;

/**
 * Interface for a factory of {@link GameMap}s.
 */
public interface GameMapFactory {

    /**
     * @param source JSON source string
     * @return A {@link GameMap} created from a JSON string
     */
    GameMap fromJSON(String source);

    /**
     * @param fileName The filename relative to resources
     * @return A {@link GameMap} created from a JSON file
     * @throws IOException if an I/O error occurs
     */
    GameMap fromJSONFile(String fileName) throws IOException;

    /**
     * @param name The name of the {@link GameMap} as defined in the resources
     * @return A {@link GameMap} created from the available resources
     */
    GameMap fromName(String name);
}
