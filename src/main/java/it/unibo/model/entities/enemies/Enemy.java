package it.unibo.model.entities.enemies;

import it.unibo.model.entities.IMovableEntity;

/**
 * Represents the enemy entity.
 */
public interface Enemy extends IMovableEntity {
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
     * @param damage
     * @return the damage dealt to the enemy.
     */
    int getDamage(int damage);

    /**
     * Represents the image of the enemy.
     * 
     * @return the image path of enemy image.
     */
    String getImagePath();

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

    /**
     * Run enemy thread.
     */
    void startMoving();

    /**
     * Deactivate an enemy when its damage has already been taken into account.
     */
    void deactivate();
}
