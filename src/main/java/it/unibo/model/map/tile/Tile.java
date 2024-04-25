package it.unibo.model.map.tile;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.utilities.Position2D;

/**
 * Represents a square 2-dimensional tile that forms the {@link it.unibo.model.map.GameMap GameMap}.
 * If {@link #canBuild} returns {@code true} it is possible
 * to build a {@link Tower} through {@link #buildTower}.
 */
public interface Tile {
    /**
     * @return {@link Position2D} of the bottom-left corner of the {@link Tile}
     */
    Position2D getPosition2d();

    /**
     * @return the length of the {@link Tile}'s sides
     */
    double getSize();

    /**
     * Checks whether the given {@link Position2D} is inside the bounds
     * of the {@link Tile}.
     * @param point The {@link Position2D} to check
     * @return {@code true} if the point is within the bounds of the
     * {@link Tile}, {@code false} otherwise
     */
    boolean isInBounds(Position2D point);

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
     * Destroys the currently built {@link Tower} if present.
     */
    void destroyTower();

    /**
     * @return the path of the file representing the {@link Tile}
     */
    String getSprite();
}
