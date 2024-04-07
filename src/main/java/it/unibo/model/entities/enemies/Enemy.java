package it.unibo.model.entities.enemies;

import it.unibo.model.entities.Entity;

/**
 * Represents the enemy entity.
 */
public interface Enemy extends Entity{

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
    int inflictDamage();

    /**
     * Check if the enemy is alive or not.
     * 
     * @return true if alive, false otherwise.
     */
    boolean isAlive();
}
