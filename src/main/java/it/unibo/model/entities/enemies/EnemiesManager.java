package it.unibo.model.entities.enemies;

import java.util.ArrayList;
import java.util.Set;

import it.unibo.model.core.GameObserver;
import it.unibo.model.map.GameMap;

/**
 * Interface representing the manager for handling enemies in the game.
 * Extends GameObserver to observe game state changes.
 */
public interface EnemiesManager extends GameObserver {

    /**
     * Set the actual map.
     *
     * @param gameMap The game map to set.
     */
    void setMap(GameMap gameMap);

    /**
     * Parses enemy data from a JSON file and initializes them.
     */
    void parseEnemies();

    /**
     * Constructs a new enemy entity and adds it to the game.
     *
     * @param gameMap The game map where the enemy will be placed.
     * @param enemyName The name of the enemy.
     * @param type The type of the enemy.
     * @param imgPath The path to the image of the enemy.
     * @param lp The life points of the enemy.
     * @param reward The reward points given when the enemy is defeated.
     */
    void buildEnemy(GameMap gameMap, String enemyName, String type, String imgPath, int lp, int reward);

    /**
     * Retrieves the set of currently active enemies.
     *
     * @return The set of currently alive enemies.
     */
    Set<Enemy> getCurrentEnemies();

    /**
     * Checks if there are no enemies currently active.
     *
     * @return True if there are no enemies alive, false otherwise.
     */
    boolean noMoreRunningEnemies();

    /**
     * Pushes a new enemy into the game based on the provided ID.
     *
     * @param id The identifier of the enemy to push.
     */
    void pushEnemy(int id);

    /**
     * Retrieves the number of enemies that are currently alive.
     *
     * @param enemies The list of enemies to check.
     * @return The number of enemies that are alive.
     */
    long getEnemiesAlive(ArrayList<Enemy> enemies);

    /**
     * Retrieves the total number of different enemy types available.
     *
     * @return The number of different enemy types.
     */
    int getNEnemyTypes();

    /**
     * Toggles the pause state of the enemy manager.
     */
    void togglePause();

    /**
     * Retrieves the number of player lives lost since the last call.
     *
     * @return The number of player lives lost.
     */
    int getNumberOfPlayerLivesLost();

    /**
     * Retrieves the total reward points earned by the player since the last call.
     *
     * @return The reward points earned by the player.
     */
    int getPLayerReward();
}
