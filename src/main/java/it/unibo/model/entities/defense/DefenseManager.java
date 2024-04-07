package it.unibo.model.entities.defense;

import java.util.Set;

import it.unibo.model.entities.enemies.Enemy;
import it.unibo.utilities.*;

/**
 * Represents the entity manager to manage all the entities.
 */
public interface DefenseManager {
    
    /**
     * Builds the entity thread.
     */
    void buildTower(String filename);

    /**
     * Represents the enemies.
     * 
     * @return a set of all the active enemies.
     */
    Set<Enemy> getEnemies();
    
    /**
     * Represents the towers.
     * 
     * @return a set of all the active towers.
     */
    Set<Tower> getTowers();

    /**
     * Represents the nearest enemy to hit from a tower position.
     * 
     * @return the nearest enemy to hit.
     */
    Enemy getNearestEnemy(Position2D position, int radius);
}
