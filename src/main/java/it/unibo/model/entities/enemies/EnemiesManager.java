package it.unibo.model.entities.enemies;
import java.util.Optional;
import java.util.Set;

import it.unibo.model.map.GameMap;
import it.unibo.model.utilities.Position2D;

public interface EnemiesManager {

    /**
     * Set the actual map.
     * @param gameMap
     */
    void setMap(GameMap gameMap);

    /**
     * Parse enemies from JSON file.
     */
    void parseEnemies();

    /**
     * Build an enemy entity.
     */
    void buildEnemy(GameMap gameMap, String enemyName, String type, String imgPath, int lp, int reward);

    /*
     * Uptate directions of all the currently alive enemies
     */
    void updateEnemiesDirections(long currentTimeMillis);

    /**
     * Represents the enemies.
     * 
     * @return the set of currently alive enemies.
     */
    Set<Enemy> getCurrentEnemies();

    /**
     * Represents the nearest enemy.
     * 
     * @return the nearest enemy.
     */
    Optional<Enemy> getNearestEnemy(Position2D position2d, int radius);
}
