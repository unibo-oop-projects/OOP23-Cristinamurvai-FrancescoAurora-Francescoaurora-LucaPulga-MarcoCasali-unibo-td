package it.unibo.controller;

import java.util.Map;
import java.util.Set;
import it.unibo.model.entities.Entity;

/**
 * Represents a controller following the MVC pattern.
 */
public interface GameController {
    /**
     * Starts the game, requires a map
     * to be set through {@link #setGameMap}
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
     */
    void setGameMap(String name);
    /**
     * @return The set of entities currently
     * present
     */
    Set<Entity> getEntities();
}
