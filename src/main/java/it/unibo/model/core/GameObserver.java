package it.unibo.model.core;

/**
 * Represents an observer of the game.
 */
public interface GameObserver {
    /**
     * This method will be called regularly
     * by the internal game engine to synchronize
     * updates.
     * @param gameState The current game state
     */
    void update(GameState gameState);
}
