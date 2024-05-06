package it.unibo.model.map.tile;

/**
 * Represents the possible features of a
 * {@link it.unibo.model.map.tile.Tile Tile}
 */
public enum TileFeature {
    /**
     * Start of the enemies' path
     */
    PATH_START,
    /**
     * End of the enemies' path
     */
    PATH_END,
    /**
     * Part of the enemies' path
     */
    PATH,
    /**
     * Allows building towers
     */
    DEFENSE
}
