package it.unibo.model.map.tile;

import java.io.IOException;
import it.unibo.model.utilities.Position2D;

/**
 * Interface for a factory of {@link Tile}s.
 */
public interface TileFactory {
    /**
     * @param fileName The file name
     * @param position The {@link Position2D} of the {@link Tile}
     * @return A {@link Tile} created from a JSON file
     * @throws IOException if an I/O error occurs
     */
    Tile fromJSONFile(String fileName, Position2D position) throws IOException;

    /**
     * @param jsonString The source string in JSON format
     * @param position The {@link Position2D} of the {@link Tile}
     * @return A {@link Tile} created from a JSON string
     */
    Tile fromJSON(String jsonString, Position2D position);

    /**
     * @param name The name of the {@link Tile} as defined in the resources
     * @param position The {@link Position2D} of the {@link Tile}
     * @return A {@link Tile} created from the available resources
     * @throws IOException if an I/O error occurs
     */
    Tile fromName(String name, Position2D position) throws IOException;
}
