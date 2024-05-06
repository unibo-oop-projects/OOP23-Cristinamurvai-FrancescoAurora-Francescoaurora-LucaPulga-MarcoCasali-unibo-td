package it.unibo.model.engine;

import it.unibo.model.entities.Player;
import it.unibo.model.map.GameMap;

/**
 * Represents the engine of the game.
 */
public interface GameEngine {
    /**
     * Starts the game.
     */
    void start();
    /**
     * Toggles the state of the game between
     * paused and running.
     */
    void togglePause();
    /**
     * @return {@code True} if the game is running,
     * {@code False} otherwise
     */
    boolean isRunning();
    /**
     * @param map The {@link GameMap} to play
     */
    void setGameMap(GameMap map);
    /**
     * @return The {@link GameMap} instance currently loaded
     */
    GameMap getGameMap();
    /**
     * @return The {@link Player} instance of the game
     */
    Player getPlayer();
}
