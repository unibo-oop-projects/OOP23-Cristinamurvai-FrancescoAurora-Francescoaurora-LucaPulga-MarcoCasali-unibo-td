package it.unibo.model.map.tile;

import it.unibo.model.utilities.Position2D;

/**
 * Implementation of {@link Tile}.
 */
public class TileImpl implements Tile {
    private static final String TILE_RESOURCES = "tiles/";
    private final String spriteLocation;
    private final double size;
    private final Position2D position;

    /**
     * @param spriteName Name of the sprite
     * @param size Size of the sides
     * @param position Position of the bottom-left corner
     */
    public TileImpl(final String spriteName, final double size, final Position2D position) {
        this.position = position;
        this.size = size;
        this.spriteLocation = TILE_RESOURCES + spriteName;
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
    public boolean isInBounds(final Position2D point) {
        return (point.x() <= this.position.x() + this.size) && (point.x() >= this.position.x())
                && (point.y() <= this.position.y() + this.size) && (point.y() >= this.position.y());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSprite() {
        return this.spriteLocation;
    }
}
