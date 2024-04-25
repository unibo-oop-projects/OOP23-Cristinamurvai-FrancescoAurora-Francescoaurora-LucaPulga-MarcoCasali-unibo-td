package it.unibo.model.map.tile;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.utilities.Position2D;

/**
 * Abstract implementation of {@link Tile} with common methods.
 */
public abstract class AbstractTile implements Tile {
    private static final String TILE_RESOURCES = "tiles/";
    private final double size;
    private final Position2D position;
    private final String sprite;

    /**
     * @param size The size of the {@link Tile}'s sides
     * @param position The {@link Position2D} of the bottom left corner of the {@link Tile}
     * @param sprite The filename of the sprite representing this {@link Tile}
     */
    public AbstractTile(final double size, final Position2D position, final String sprite) {
        this.size = size;
        this.position = position;
        this.sprite = TILE_RESOURCES + sprite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position2D getPosition2d() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSize() {
        return this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSprite() {
        return this.sprite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInBounds(final Position2D point) {
        return (point.x() >= this.position.x()) && (point.x() <= this.position.x() + this.size)
                && (point.y() >= this.position.y()) && (point.y() <= this.position.y() + this.size);
    }

    /**
     * {@inheritDoc}
     */
    public abstract void buildTower(Tower tower);

    /**
     * {@inheritDoc}
     */
    public abstract boolean canBuild();

    /**
     * {@inheritDoc}
     */
    public abstract void destroyTower();
}
