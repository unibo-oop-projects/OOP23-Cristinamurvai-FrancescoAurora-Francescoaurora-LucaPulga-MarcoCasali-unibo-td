package it.unibo.model.map;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import it.unibo.model.map.tile.Tile;
import it.unibo.model.map.tile.TileFeature;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Implementation of {@link GameMap}.
 */
public class GameMapImpl implements GameMap {
    private final double tileSize;
    private final Map<Position2D, Tile> tiles;

    /**
     * @param tiles The {@link Tile}s mapped to their position
     * @param tileSize The length of a single {@link Tile}
     */
    public GameMapImpl(final Map<Position2D, Tile> tiles, final double tileSize) {
        this.tiles = tiles;
        this.tileSize = tileSize;
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
        final Set<TileFeature> directions = this.tiles.get(edge(position)).getTileFeatures();
        if (directions.contains(TileFeature.MOVE_DOWN)) {
            return new Vector2D(0, -1);
        } else if (directions.contains(TileFeature.MOVE_UP)) {
            return new Vector2D(0, 1);
        } else if (directions.contains(TileFeature.MOVE_RIGHT)) {
            return new Vector2D(1, 0);
        } else if (directions.contains(TileFeature.MOVE_LEFT)) {
            return new Vector2D(-1, 0);
        } else {
            throw new IllegalStateException();
        }
    }

    private Position2D edge(final Position2D position) {
        return new Position2D((int) (position.x() / this.tileSize), (int) (position.y() / this.tileSize));
    }
}
