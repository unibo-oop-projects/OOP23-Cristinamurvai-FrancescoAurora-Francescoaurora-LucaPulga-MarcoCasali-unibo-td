package it.unibo.model.entities.enemies;
import java.util.Optional;
import java.util.Set;
import java.util.ArrayList;

import it.unibo.model.map.GameMap;
import it.unibo.model.utilities.Position2D;

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
     *  @param id
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

    /**
     * Represents the nearest enemy.
     * 
     * @param position2d
     * @param radius
     * 
     * @return the nearest enemy.
     */
    Optional<Enemy> getNearestEnemy(Position2D position2d, int radius);
}
