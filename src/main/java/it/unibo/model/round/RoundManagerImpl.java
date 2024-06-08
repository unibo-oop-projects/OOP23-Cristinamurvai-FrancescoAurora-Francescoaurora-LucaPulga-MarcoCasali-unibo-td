package it.unibo.model.round;

import java.util.List;
import java.util.Random;

import it.unibo.model.entities.enemies.EnemiesManager;
import it.unibo.model.entities.enemies.EnemiesManagerImpl;

/**
 * Implements of interface Management of rouds.
 */
public class RoundManagerImpl {


    private static final int ROUND_TIME = 30; // tempo del conto alla rovescia in secondi
    private Thread countdownThread;
    private Thread sequentialThread;
    private boolean interrupted = false;
    private int currentTime; // tempo corrente in secondi
    private final Object lock = new Object();
    private final EnemiesManager enemies;
    private RoundImp round;
    private double timeSpawn;
    private List<Integer> listEnemies;
    private Random random;
    private static final int MINUTES_SECONDS_IN_HOURS_MINUTES = 60;
    private static final double ADVANCEMENT_TIME = 0.1;

    /**
     * Constructor method, initialise variables.
     * @param enemiesManager to build enemies and verify alive
     */
    public RoundManagerImpl(final EnemiesManager enemiesManager) {
        enemies = enemiesManager;
        round = new RoundImp(2); //change with get enemies
        random = new Random();
    }

    /**
     * Method for Starting the Game CountDown.
     */
    private void startCountdown() {
        if (interrupted) {
            return;
        }

        if (sequentialThread != null && sequentialThread.isAlive()) {
            interrupted = true;
            try {
                sequentialThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        interrupted = false;
        synchronized (lock) {
            currentTime = ROUND_TIME;
        }
        countdownThread = new Thread(new CountdownTask());
        countdownThread.start();
    }

    /**
     * Countdown thread class.
     */
    private final class CountdownTask implements Runnable {
        /**
         * method for starting the thread.
         */
        @Override
        public void run() {
            for (int i = ROUND_TIME; i > 0; i--) {
                synchronized (lock) {
                    if (interrupted) {
                        return;
                    }
                    currentTime = i;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
            startSequential();
        }
    }

    /**
     * sequential round account method.
     */
    private void startSequential() {
        if (interrupted) {
            return;
        }

        round.increaseRoud();
        timeSpawn = round.getTimeSpawn();
        listEnemies = round.getEnemiesSpawn();
        sequentialThread = new Thread(new SequentialTask());
        sequentialThread.start();
    }

    /**
     * Sequential thread class.
     */
    private final class SequentialTask implements Runnable {
        /**
         * method for starting the thread and logic of enemy creation.
         */
        @Override
        public void run() {
            double seconds = 0;
            double spawnCounter = 0; // counter for the creation of enemies
            while (!interrupted) {
                synchronized (lock) {
                    currentTime = (int) seconds;
                }

                spawnCounter += ADVANCEMENT_TIME;
                if (spawnCounter >= timeSpawn && listEnemies.stream().mapToInt(Integer::intValue).sum() != 0) {
                    int enemyIndex = random.nextInt(2); //change with get enemis
                    boolean spawn = false;
                    while (!spawn) {
                        if (listEnemies.get(enemyIndex) != 0) {
                            // Inserire qui il costruttore del nemico
                            spawn = true;
                        } else {
                            enemyIndex++;
                            enemyIndex = enemyIndex % 2;
                        }
                    }

                    spawnCounter -= timeSpawn;
                } else {
                    if (listEnemies.stream().mapToInt(Integer::intValue).sum() == 0) { //aggiungere isAlive
                        interrupted = true;
                    }
                }

                try {
                    Thread.sleep(100); // sleep for 0.1 seconds
                } catch (InterruptedException e) {
                    return;
                }
                seconds += ADVANCEMENT_TIME;
            }
            interrupted = false;
            startCountdown();
        }

        /**
         * Conversion of seconds to minutes and seconds.
         * @param totalSeconds seconds stored in the thread
         * @return minutes and seconds
         */
        private String secondsToTimeFormat(final int totalSeconds) {
            int minutes = totalSeconds / MINUTES_SECONDS_IN_HOURS_MINUTES;
            int seconds = totalSeconds % MINUTES_SECONDS_IN_HOURS_MINUTES;
            return String.format("%02d:%02d", minutes, seconds);
        }
    }

    /**
     * Public call for time.
     * @return time if active
     */
    public String getTime() {
        synchronized (lock) {
            if (countdownThread != null && countdownThread.isAlive()) {
                return "Countdown: " + currentTime + " seconds";
            } else if (sequentialThread != null && sequentialThread.isAlive()) {
                return "Sequential count: " + secondsToTimeFormat(currentTime);
            } else {
                return "No active timers";
            }
        }
    }

    /**
     * Conversion of seconds to minutes and seconds.
     * @param totalSeconds seconds stored in the thread
     * @return minutes and seconds
     */
    private String secondsToTimeFormat(final int totalSeconds) {
        int minutes = totalSeconds / MINUTES_SECONDS_IN_HOURS_MINUTES;
        int seconds = totalSeconds % MINUTES_SECONDS_IN_HOURS_MINUTES;
        return String.format("%02d:%02d", minutes, seconds);
    }


    /**
     * End-of-game method, usable when the player has run out of lives.
     */
    public void gameOver() {
        interrupted = true;

        if (countdownThread != null && countdownThread.isAlive()) {
            countdownThread.interrupt();
            try {
                countdownThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (sequentialThread != null && sequentialThread.isAlive()) {
            sequentialThread.interrupt();
            try {
                sequentialThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Method for starting the game.
     */
    public void startGame() {
        startCountdown();
    }
}
