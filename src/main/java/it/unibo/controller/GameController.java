package it.unibo.controller;

import java.util.Map;
import it.unibo.model.core.GameObserver;
import it.unibo.model.map.GameMap;
import it.unibo.view.GameView;

/**
 * Represents a controller following the MVC pattern.
 */
public interface GameController extends GameObserver {
    /**
     * Starts the game, requires a map
     * to be set through {@link #setGameMap}.
     */
    void startGame();
    /**
     * Pauses/unpauses the game.
     */
    void togglePause();
    /**
     * @return A map of available maps names
     * and the location of a preview image
     */
    Map<String, String> getAvailableMaps();
    /**
     * @param name The name of the map to play,
     * chosen from the list in {@link #getAvailableMaps}
     * @return The instantiated {@link GameMap}
     */
    GameMap setGameMap(String name);
    /**
     * Registers a view.
     * @param view The {@link GameView}
     */
    void registerView(GameView view);
}
