package it.unibo.model.entities.enemies;
import java.util.Set;

import it.unibo.model.utilities.Position2D;

public interface EnemiesManager {
    /**
     * Build an enemy entity.
     */
    void buildEnemy(String enemyName);

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
    Enemy getNearestEnemy(Position2D position2d, int radius);
}
