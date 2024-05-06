package it.unibo.model.map;

import java.util.stream.Stream;
import it.unibo.model.map.tile.Tile;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Represents a grid of {@link Tile}s with a fixed path.
 */
public interface GameMap {
    /**
     * @return A {@link Stream} of the map's {@link Tile}s
     */
    Stream<Tile> getTiles();

    /**
     * @return A {@link Stream} of {@link Tile}s that allow buildings
     */
    Stream<Tile> getDefenseTiles();

    /**
     * @return The {@link Position2D} where the path starts
     */
    Position2D getSpawnPosition();

    /**
     * @param position The current Position of the enemy
     * @return A {@link Vector2D} that follows the path's direction
     */
    Vector2D getPathDirection(Position2D position);

}
