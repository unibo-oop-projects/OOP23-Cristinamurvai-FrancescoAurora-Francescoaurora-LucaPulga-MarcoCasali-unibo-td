package it.unibo.model.map.tile;

import java.util.Optional;
import java.util.Set;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.utilities.Position2D;

/**
 * Implementation of {@link Tile}.
 */
public class TileImpl implements Tile {
    private static final String TILE_RESOURCES = "tiles/";
    private final String spriteLocation;
    private final Position2D position;
    private Optional<Tower> tower = Optional.empty();
    private final Set<TileFeature> tileFeatures;

    /**
     * @param spriteName Name of the sprite
     * @param size Size of the sides
     * @param position Position of the bottom-left corner
     */
    public TileImpl(final String spriteName, final Position2D position, final Set<TileFeature> tileFeatures) {
        this.position = position;
        this.spriteLocation = TILE_RESOURCES + spriteName;
        this.tileFeatures = tileFeatures;
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
    public String getSprite() {
        return this.spriteLocation;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canBuild() {
        return this.tower.isEmpty() && this.tileFeatures.contains(TileFeature.DEFENSE);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<TileFeature> getTileFeatures() {
        return this.tileFeatures;
    }
}
