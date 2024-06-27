package it.unibo.model.entities.enemies;

import java.util.ArrayList;
import java.util.Set;

import it.unibo.model.map.GameMap;

public interface EnemiesManager {

    /**
     * Set the actual map.
     *
     * @param gameMap
     */
    void setMap(GameMap gameMap);

    /**
     * Parse enemies from JSON file.
     */
    void parseEnemies();

    /**
     * Build an enemy entity.
     *
     * @param gameMap
     * @param enemyName
     * @param type
     * @param imgPath
     * @param lp
     * @param reward
     */
    void buildEnemy(GameMap gameMap, String enemyName, String type, String imgPath, int lp, int reward);

    /**
     * Uptate directions of all the currently alive enemies.
     *
     * @param currenTimeMillis
     */
    void updateEnemiesDirections(long currentTimeMillis);

    /**
     * Represents the enemies.
     *
     * @return the set of currently alive enemies.
     */
    Set<Enemy> getCurrentEnemies();

    /**
     * Push the enemies.
     *
     * @param id
     */
    void pushEnemy(int id);

    /**
     * get the enemies that are still alive.
     *
     * @param enemies
     *
     * @return the number of enemies alive.
     */
    long getEnemiesAlive(ArrayList<Enemy> enemies);

    /* 
     * @return the damage caused to player by enemies that reached the end of the path.
     */
    int getDamageToPlayerLife();

    /* 
     * @return the number of different enemy types.
     */
    int getNEnemyTypes();
}
