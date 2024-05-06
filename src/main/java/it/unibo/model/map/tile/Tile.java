package it.unibo.model.map.tile;

import java.util.Set;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.utilities.Position2D;

/**
 * Represents a square 2-dimensional tile that forms the {@link it.unibo.model.map.GameMap GameMap}.
 */
public interface Tile {
    /**
     * @return {@link Position2D} of the bottom-left corner of the {@link Tile}
     */
    Position2D getPosition2d();

    /**
     * @return The set of {@link TileFeature}
     */
    Set<TileFeature> getTileFeatures();

    /**
     * @return the path of the file representing the {@link Tile}
     */
    String getSprite();

    /**
     * @return {@code true} if the {@link Tile} is empty and allows
     * buildings, {@code false} otherwise
     */
    boolean canBuild();

    /**
     * Occupies the current {@link Tile} with a {@link Tower}.
     * @param tower The {@link Tower} to build
     */
    void buildTower(Tower tower);

    /**
     * Destroys the built {@link Tower} if present.
     */
    void destroyTower();
}
