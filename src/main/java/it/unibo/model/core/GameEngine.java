package it.unibo.model.core;

import it.unibo.model.entities.defense.tower.Tower;
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
     * Toggles the state of the game between paused and running.
     */
    void togglePause();

    /**
     * @param map The {@link GameMap} to play
     */
    void setGameMap(GameMap map);

    /**
     * Builds the specified {@link Tower} in the specified position if allowed.
     *
     * @param tower Tower to build
     */
    void buildTower(Tower tower);

    /**
     * @param observer A {@link GameObserver} that will receive updates from the
     * engine
     */
    void registerObserver(GameObserver observer);
}
