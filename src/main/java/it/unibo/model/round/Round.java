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
     *
     * @return timeSpawn
     */
    double getTimeSpawn();

    /**
     * Get method for EnemisSpawn.
     *
     * @return vector of enemisSpawn
     */
    List<Integer> getEnemiesSpawn();

    /**
     * Get actual roud.
     *
     * @return rouds+1
     */
    int getRoud();

    /**
     * Getting if it's the last round.
     *
     * @return answer to the question (true or false)
     */
    boolean getLastRound();
}
