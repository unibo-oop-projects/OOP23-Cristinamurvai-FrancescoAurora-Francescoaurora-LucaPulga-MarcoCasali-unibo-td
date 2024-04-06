package it.unibo.model.entities.api;

import java.util.Set;
import it.unibo.utilities.*;

/**
 * Represents the entity manager to manage all the entities.
 */
public interface DefenseManager {
    
    /**
     * Builds the entity thread.
     */
    void buildTower(Tower entity);

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
     * Represents the bullets.
     * 
     * @return a set of all the active bullets.
     */
    Set<Bullet> getBullets();

    /**
     * Represents the weapons.
     * 
     * @return a set of all the active weapons.
     */
    Set<Weapon> getWeapons();

    /**
     * Represents the nearest enemy to hit from a tower position.
     * 
     * @return the nearest enemy to hit.
     */
    Enemy getNearestEnemy(Position2D position, int radius);
}
