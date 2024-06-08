package it.unibo.model.round;

import java.util.List;

/**
 * Interface for roud manager.
 */
public interface Round {

    /**
     * Increase Round and update parameters.
     */
    void increaseRoud();

    /**
     * Get method for TimeSpawn.
     * @return timeSpawn
     */
    double getTimeSpawn();

    /**
     * Get method for EnemisSpawn.
     * @return vector of enemisSpawn
     */
    List<Integer> getEnemiesSpawn();

    /**
     * Get actual roud.
     * @return rouds+1
     */
    int getRoud();
}
