package it.unibo.model.map;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import it.unibo.model.map.tile.Tile;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Implementation of {@link GameMap}.
 */
public class GameMapImpl implements GameMap {
    private static final String MAP_RESOURCES = "/maps/";
    private final String mapLocation;
    private final int rows = 5;
    private final int columns = 10;
    private final double tileSize = 20;
    private Map<Integer, Tile> defenseTiles = new HashMap<>();

    /**
     * @param mapName Filename of the map
     */
    public GameMapImpl(final String mapName) {
        this.mapLocation = MAP_RESOURCES + mapName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<Tile> getDefenseTiles() {
        return this.defenseTiles.values().stream();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position2D getSpawnPosition() {
        return new Position2D(0, 50);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getPathDirection(final Position2D position) {
        return new Vector2D(1, 0);
    }
}
