package it.unibo.model.engine;

/**
 * Represents the possible states of the game.
 */
public enum GameState {
    /**
     * Game instance created and ready to start
     */
    INITIALIZED,
    /**
     * Game is paused
     */
    PAUSED,
    /**
     * Game is running
     */
    RUNNING,
    /**
     * Game is over
     */
    GAME_OVER
}
