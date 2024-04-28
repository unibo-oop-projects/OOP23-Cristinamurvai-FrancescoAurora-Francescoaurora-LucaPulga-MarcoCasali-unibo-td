package it.unibo.model.map.tile;

import java.util.Optional;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.utilities.Position2D;

/**
 * Implementation of {@link DefenseTile}.
 */
public class DefenseTileImpl extends TileImpl implements DefenseTile {
    private Optional<Tower> tower = Optional.empty();

    /**
     * @param spriteName Name of the sprite
     * @param size Size of the sides
     * @param position Position of the bottom-left corner
     */
    public DefenseTileImpl(final String spriteName, final double size, final Position2D position) {
        super(spriteName, size, position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canBuild() {
        return this.tower.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildTower(final Tower tower) {
        this.tower = Optional.of(tower);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroyTower() {
        this.tower = Optional.empty();
    }
}
