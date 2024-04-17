package it.unibo.model.entities.defense.manager;

import java.util.List;
import java.util.Set;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;

/**
 * Represents the entity manager to manage all the entities.
 */
public interface DefenseManager {
    /**
     * @param filename
     * Builds the entity thread.
    */
    void buildTower(Tower tower);

    /**
     * Represents the enemies.
     * @return a set of all the active enemies.
    */
    Set<Enemy> getEnemies();
    
    /**
     * Represents the towers.
     * @return a set of all the active towers.
    */
    Set<Tower> getTowers();

    /**
     * Represents the nearest enemy to hit from a tower position.
     * @param position
     * @param radius
     * @return the nearest enemy to hit.
    */
    Enemy getNearestEnemy(Position2D position, int radius);

    /**
     * Activate all towers.
     * @param towers
     */
    void activateAllTowers(List<Tower> towers);

    /**
     * Stop all threads dedicated to towers.
     */
    void stopAllTowers();
}
