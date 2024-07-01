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
    int getCurrentLP();

    /**
     * Represents the reward received after killing an enemy.
     *
     * @return the reward received after killing an enemy.
     */
    int getReward();

    /**
     * Represents the damage dealt to the enemy.
     *
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
     * Run enemy thread.
     */
    void startMoving();

    /**
     * Deactivate the enemy when its damage has already been taken into account.
     */
    void deactivate();

    /*
     * Pause the enemy.
     */
    void pause();

    /*
     * Resume the enemy from pause.
     */
    void resume();

    /*
     * Move the enemy of a scalded Vector2D.
     */
    void move();
}
