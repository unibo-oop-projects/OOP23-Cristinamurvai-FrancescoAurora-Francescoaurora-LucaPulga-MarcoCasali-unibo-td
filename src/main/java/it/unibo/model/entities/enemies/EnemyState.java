package it.unibo.model.entities.enemies;

/**
 * Represents the possible states of an enemy.
 */
public enum EnemyState {
    /**
     * the enemy is ready to be spawned.
     */
    READY,

    /**
     * The enemy is currently moving along its path.
     */
    MOVING,

    /**
     * The enemy is paused.
     */
    PAUSED,

    /**
     * The enemy has been defeated and is no longer active.
     */
    DEAD,

    /**
     * The enemy has reached the end of the path.
     */
    FINISHED,

    /**
     * The enemy is inactive.
     */
    INACTIVE
}
