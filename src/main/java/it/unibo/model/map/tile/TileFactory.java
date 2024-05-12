package it.unibo.model.map.tile;

import it.unibo.model.utilities.Position2D;

/**
 * Interface for a factory of {@link Tile}s
 */
public interface TileFactory {
    /**
     * @param path The path of the file
     * @param position The {@link Position2D} of the {@link Tile}
     * @return A {@link Tile} created from a JSON file
     */
    Tile fromJSONFile(String path, Position2D position);
    /**
     * @param jsonString The source string in JSON format
     * @param position The {@link Position2D} of the {@link Tile}
     * @return A {@link Tile} created from a JSON string
     */
    Tile fromJSON(String jsonString, Position2D position);
    /**
     * @param name The name of the {@link Tile}
     * @param position The {@link Position2D} of the {@link Tile}
     * @return A {@link Tile} created from the available resources
     */
    Tile fromName(String name, Position2D position);
}
