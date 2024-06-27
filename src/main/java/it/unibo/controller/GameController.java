package it.unibo.controller;

import java.util.List;

import it.unibo.model.core.GameObserver;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.map.GameMap;
import it.unibo.view.GameView;

/**
 * Represents a controller following the MVC pattern.
 */
public interface GameController extends GameObserver {

    /**
     * Starts the game, requires a map to be set through {@link #setGameMap}.
     */
    void startGame();

    /**
     * Pauses/unpauses the game.
     */
    void togglePause();

    /**
     * @return A list of available maps names
     */
    List<String> getAvailableMaps();

    /**
     * Builds the specified {@link Tower} in the specified position if allowed.
     *
     * @param id The id of the tower
     * @param pos The position where to build
     */
    void buildTower(Tower tower);

    /**
     * @param name The name of the map to play, chosen from the list in
     * {@link #getAvailableMaps}
     * @return The instantiated {@link GameMap}
     */
    GameMap setGameMap(String name);

    /**
     * Registers a view.
     *
     * @param view The {@link GameView}
     */
    void registerView(GameView view);
}
