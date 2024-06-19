package it.unibo.model.round;

import java.util.List;
import java.util.Random;

import it.unibo.model.entities.enemies.EnemiesManager;

/**
 * Implements of interface Management of rouds.
 */
public class RoundManagerImpl {


    private static final int ROUND_TIME = 2; // tempo del conto alla rovescia in secondi
    private Thread countdownThread = null;
    private Thread sequentialThread = null;
    private boolean interrupted = false;
    private int currentTime; // tempo corrente in secondi
    private final Object lock = new Object();
    private final EnemiesManager enemies;
    private final Round round;
    private double timeSpawn;
    private List<Integer> listEnemies;
    private final Random random;
    private static final int MINUTES_SECONDS_IN_HOURS_MINUTES = 60;
    private static final double ADVANCEMENT_TIME = 0.1;
    private boolean isPaused = false;

    /**
     * Constructor method, initialise variables.
     * @param enemiesManager to build enemies and verify alive
     */
    public RoundManagerImpl(final EnemiesManager enemiesManager) {
        enemies = enemiesManager;
        round = new RoundImp(2); //TODO change with get enemies
        random = new Random();
    }

    /**
     * Method for Starting the Game CountDown.
     */
    private void startCountdown() {
        if (interrupted) {
            return;
        }
        countdownThread = null;
        synchronized (lock) {
            currentTime = ROUND_TIME;
        }
        countdownThread = null;
        round.increaseRoud();
        timeSpawn = round.getTimeSpawn();
        listEnemies = round.getEnemiesSpawn();
        if(round.getLastRound() == true){
            return;
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
                while (isPaused == true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
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
            int numEnemiesSpawn = countNonZeroEnemies();
            while (!interrupted) {
                while (isPaused == true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                synchronized (lock) {
                    currentTime = (int) seconds;
                }
                if (spawnCounter >= timeSpawn && listEnemies.stream().mapToInt(Integer::intValue).sum() != 0) {
                    int enemyIndex = random.nextInt(numEnemiesSpawn);
                    boolean spawn = false;
                    while (!spawn) {
                        if (listEnemies.get(enemyIndex) != 0) {
                            // Inserire qui il costruttore del nemico
                            enemies.pushEnemy(enemyIndex);
                            spawn = true;
                            listEnemies.set(enemyIndex, listEnemies.get(enemyIndex) - 1);
                        } else {
                            enemyIndex++;
                            enemyIndex = enemyIndex % numEnemiesSpawn;
                        }
                    }
                    spawnCounter -= timeSpawn;
                } else {
                    if (listEnemies.stream().mapToInt(Integer::intValue).sum() == 0) { //TODO aggiungere isAlive
                        interrupted = true;
                    }
                }

                try {
                    Thread.sleep(100); // sleep for 0.1 seconds
                } catch (InterruptedException e) {
                    return;
                }
                seconds += ADVANCEMENT_TIME;
                spawnCounter += ADVANCEMENT_TIME;
            }
            interrupted = false;
            startCountdown();
        }
        /**
         * Count the types of enemies to be spawned.
         * @return types of enemies
         */
        private int countNonZeroEnemies() {
            return (int) listEnemies.stream().filter(e -> e != 0).count();
        }
    }

    /**
     * Public call for time.
     * @return time if active
     */
    public String getTime() {
        synchronized (lock) {
            if (countdownThread != null && countdownThread.isAlive()) {
                return " " + currentTime + " seconds";
            } else if (sequentialThread != null && sequentialThread.isAlive()) {
                return "Sequential count: " + secondsToTimeFormat(currentTime);
            } else if (round.getLastRound() == true) {
                return "You win!";
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
                System.err.println(e.getMessage());
            }
        }

        if (sequentialThread != null && sequentialThread.isAlive()) {
            sequentialThread.interrupt();
            try {
                sequentialThread.join();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    /**
     * Method for starting the game.
     */
    public void startGame() {
        startCountdown();
    }

    /**
     * Method for return round number.
     * @return round number
     */
    public int getRound() {
        return round.getRoud();
    }

    /**
     * Get if it is the last round from the instance of the round.
     * @return answer to the question (true or false)
     */
    public boolean getLastRound() {
        return round.getLastRound();
    }

    /**
     * Start and Stop Pause.
     */
    public void togglePause() {
        this.isPaused = !isPaused;
    }
}
