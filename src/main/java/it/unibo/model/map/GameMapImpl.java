package it.unibo.model.map;

import java.util.Map;
import java.util.stream.Stream;
import it.unibo.model.map.tile.Tile;
import it.unibo.model.map.tile.TileFeature;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Implementation of {@link GameMap}.
 */
public class GameMapImpl implements GameMap {
    private static final String MAP_RESOURCES = "/maps/";
    private final int rows;
    private final int columns;
    private final double tileSize;
    private final Map<Position2D, Tile> tiles;

    /**
     * @param tiles The {@link Tile}s mapped to their position
     * @param tileSize The length of a single {@link Tile}
     * @param rows The number of rows of the map
     * @param columns The number of columns of the map
     */
    public GameMapImpl(final Map<Position2D, Tile> tiles, final double tileSize, final int rows, final int columns) {
        this.tiles = tiles;
        this.tileSize = tileSize;
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<Tile> getTiles() {
        return this.tiles.values().stream();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<Tile> getDefenseTiles() {
        return getTiles().filter(tile -> tile.canBuild());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position2D getSpawnPosition() {
        return this.tiles.entrySet().stream()
            .filter(entry -> entry.getValue().getTileFeatures()
            .contains(TileFeature.PATH_START)).findFirst().get().getKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getPathDirection(final Position2D position) {
        return new Vector2D(1, 0);
    }
}
