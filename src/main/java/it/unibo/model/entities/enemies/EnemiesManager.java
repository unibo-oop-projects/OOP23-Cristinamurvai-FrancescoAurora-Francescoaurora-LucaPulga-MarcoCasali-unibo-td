package it.unibo.model.entities.enemies;
import java.util.Optional;
import java.util.Set;

import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

public interface EnemiesManager {

    /**
     * Parse enemies from JSON file.
     */
    void parseEnemies();

    /**
     * Build an enemy entity.
     */
    void buildEnemy(String enemyName, String type, String imgPath, Position2D position2d, Vector2D vector2d, int lp, int reward);

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
