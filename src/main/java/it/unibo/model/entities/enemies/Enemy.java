package it.unibo.model.entities.enemies;

import it.unibo.model.entities.IMovableEntity;

/**
 * Represents the enemy entity.
 */
public interface Enemy extends IMovableEntity {

    /**
     * Move the enemy of a scalded Vector2D.
     */
    void move();

    /**
     * Run enemy thread.
     */
    void startMoving();

    /**
     * Checks if the enemy is currently alive.
     *
     * @return True if the enemy is alive, false otherwise.
     */
    boolean isAlive();

    /**
     * Deactivates the enemy, indicating it is no longer active.
     */
    void deactivate();

    /**
     * Pause the enemy.
     */
    void pause();

    /**
     * Resume the enemy from pause.
     */
    void resume();

    /**
     * Represents the actual state of the enemy.
     *
     * @return the state of the enemy.
     */
    EnemyState getState();

    /**
     * Represents the current enemy's life points.
     *
     * @return the current enemy's life points.
     */
    int getCurrentLP();

    /**
     * Represents the reward received after killing an enemy.
     *
     * @return the reward received after killing an enemy.
     */
    int getReward();

    /**
     * Deals a specified amount of damage to the enemy.
     *
     * @param damage The amount of damage to deal.
     * @return The remaining life points of the enemy after taking the damage.
     */
    int getDamage(int damage);

    /**
     * Represents the image of the enemy.
     *
     * @return the image path of enemy image.
     */
    String getImagePath();
}
