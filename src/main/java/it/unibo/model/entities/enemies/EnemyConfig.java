package it.unibo.model.entities.enemies;

/**
 * Interface representing the configuration of an enemy entity.
 */
public interface EnemyConfig {

    /**
     * Retrieves the name of the enemy.
     *
     * @return The name of the enemy.
     */
    String getEnemyName();

    /**
     * Retrieves the type of the enemy.
     *
     * @return The type of the enemy.
     */
    String getEnemyType();

    /**
     * Retrieves the path to the image of the enemy.
     *
     * @return The path to the image of the enemy.
     */
    String getEnemyImgPath();

    /**
     * Retrieves the life points of the enemy.
     *
     * @return The life points of the enemy.
     */
    int getLp();

    /**
     * Retrieves the reward points given when the enemy is defeated.
     *
     * @return The reward points given when the enemy is defeated.
     */
    int getReward();

    /**
     * Retrieves the quantity of this type of enemy available.
     *
     * @return The quantity of this type of enemy.
     */
    int getQuantity();
}
