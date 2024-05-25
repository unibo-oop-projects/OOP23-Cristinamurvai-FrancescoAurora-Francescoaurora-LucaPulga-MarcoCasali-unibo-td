package it.unibo.model.core;

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
     * @param map The {@link GameMap} to play
     */
    void setGameMap(GameMap map);
    /**
     * @param observer A {@link GameObserver} that
     * will receive updates from the engine
     */
    void registerObserver(GameObserver observer);
}
