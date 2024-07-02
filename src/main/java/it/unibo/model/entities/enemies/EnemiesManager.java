package it.unibo.model.entities.enemies;

import java.util.Set;

import it.unibo.model.core.GameObserver;
import it.unibo.model.map.GameMap;

/**
 * Interface representing the manager for handling enemies in the game.
 * Extends GameObserver to observe game state changes.
 */
public interface EnemiesManager extends GameObserver {

    /**
     * Pushes a new enemy into the game based on the provided ID.
     *
     * @param id The identifier of the enemy to push.
     */
    void pushEnemy(int id);

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
     * Toggles the pause state of the enemy manager.
     */
    void togglePause();

    /**
     * Retrieves the set of currently active enemies.
     *
     * @return The set of currently alive enemies.
     */
    Set<Enemy> getCurrentEnemies();

    /**
     * Retrieves the total number of different enemy types available.
     *
     * @return The number of different enemy types.
     */
    int getNEnemyTypes();

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

    /**
     * Set the actual map.
     *
     * @param gameMap The game map to set.
     */
    void setMap(GameMap gameMap);

    /**
     * Checks if there are no enemies currently active.
     *
     * @return True if there are no enemies alive, false otherwise.
     */
    boolean noMoreRunningEnemies();
}
