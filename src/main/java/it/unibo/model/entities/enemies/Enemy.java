package it.unibo.model.entities.enemies;

import it.unibo.model.entities.MovableEntity;

/**
 * Represents the enemy entity.
 */
public interface Enemy extends MovableEntity {
    /**
     * Represents the actual state of the enemy.
     * 
     * @return the state of the enemy.
     */
    EnemyState getState();

    /**
     * Represents the enemy's life points.
     * 
     * @return the enemy's life points.
     */
    int getLP();

    /**
     * Represents the reward received after killing an enemy.
     * 
     * @return the reward received after killing an enemy.
     */
    int getReward();

    /**
     * Represents the damage dealt to the enemy.
     * 
     * @return the damage dealt to the enemy.
     */
    int getDamage(int damage);

    /**
     * Check if the enemy is alive or not.
     * 
     * @return true if alive, false otherwise.
     */
    boolean isAlive();

    /**
     * Move the enemy of vect2d from pos2d.
     */
    void move();
}
