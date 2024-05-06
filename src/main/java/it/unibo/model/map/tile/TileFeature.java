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
     * Moves enemies left
     */
    MOVE_LEFT,
    /**
     * Moves enemies right
     */
    MOVE_RIGHT,
    /**
     * Moves enemies up
     */
    MOVE_UP,
    /**
     * Moves enemies down
     */
    MOVE_DOWN,
    /**
     * Allows building towers
     */
    DEFENSE
}
