package it.unibo.model.map.tile;

import it.unibo.model.entities.defense.tower.Tower;

/**
 * Represents a {@link Tile} where it is allowed to build one {@link Tower}
 */
public interface DefenseTile extends Tile {
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

}
