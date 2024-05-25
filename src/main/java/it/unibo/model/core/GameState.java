package it.unibo.model.core;

import java.util.Set;
import it.unibo.model.entities.Entity;
import it.unibo.model.map.GameMap;

/**
 * Represents the current state of the game.
 */
public interface GameState {
    /**
     * @return The set of entities currently
     * present
     */
    Set<Entity> getEntities();
    /**
     * @return The current {@link GameMap}
     */
    GameMap getGameMap();
    /**
     * @return The remaining money
     */
    int getMoney();
    /**
     * @return The remaining lives
     */
    int getLives();
    /**
     * @return {@code True} if the game is
     * over {@code False} otherwise
     */
    boolean isGameOver();
    /**
     * @return {@code True} if the game is
     * paused {@code False} if running
     */
    boolean isPaused();
}