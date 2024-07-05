package it.unibo.model.round;

import it.unibo.model.entities.enemies.EnemiesManager;

/**
 * Interface for roud manager.
 */
public interface RoundManager {

    /**
     * Method for starting the game.
     *
     * @param enemiesManager reference instance of enemiesManager passing
     * threats in real time
     */
    void startGame(EnemiesManager enemiesManager);

    /**
     * Method for return round number.
     *
     * @return round number
     */
    int getRound();

    /**
     * Get if it is the last round from the instance of the round.
     *
     * @return answer to the question (true or false)
     */
    boolean isLastRound();

    /**
     * Start and Stop Pause.
     */
    void togglePause();
}
