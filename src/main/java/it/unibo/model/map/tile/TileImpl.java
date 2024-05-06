package it.unibo.model.map.tile;

import java.util.Optional;
import java.util.Set;
import it.unibo.model.entities.defense.tower.Tower;

/**
 * Implementation of {@link Tile}.
 */
public class TileImpl implements Tile {
    private static final String TILE_RESOURCES = "tiles/";
    private final String spriteLocation;
    private Optional<Tower> tower = Optional.empty();
    private final Set<TileFeature> tileFeatures;

    /**
     * @param spriteName Name of the sprite
     * @param tileFeatures Set of the Tiles' features
     */
    public TileImpl(final String spriteName, final Set<TileFeature> tileFeatures) {
        this.spriteLocation = TILE_RESOURCES + spriteName;
        this.tileFeatures = tileFeatures;
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
