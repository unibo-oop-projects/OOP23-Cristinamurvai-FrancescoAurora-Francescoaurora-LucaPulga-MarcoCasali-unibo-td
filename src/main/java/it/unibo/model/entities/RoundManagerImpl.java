package it.unibo.model.entities;

import java.util.List;
import java.util.Random;
import it.unibo.model.entities.enemies.EnemiesManagerImpl;

/**
 * Implements of interface Management of rouds
 */
public class RoundManagerImpl {
    

    private static final int ROUND_TIME = 30; // tempo del conto alla rovescia in secondi
    private Thread countdownThread;
    private Thread sequentialThread;
    private boolean interrupted = false;
    private int currentTime; // tempo corrente in secondi
    private final Object lock = new Object();
    private EnemiesManagerImpl enemies;
    private RoundImp round;
    private double timeSpawn;
    private List<Integer> listEnemies;
    private Random random;

    public RoundManagerImpl(final EnemiesManagerImpl enemiesManager) {
        enemies = enemiesManager;
        round = new RoundImp(2); //change with get enemies
    }


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

    private class CountdownTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Countdown started");
            for (int i = ROUND_TIME; i > 0; i--) {
                synchronized (lock) {
                    if (interrupted) {
                        return;
                    }
                    currentTime = i;
                }
                System.out.println("Countdown: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
            startSequential();
        }
    }

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

    private class SequentialTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Sequential counting started");
            double seconds = 0;
            double spawnCounter = 0; // contatore per la creazione dei nemici
            while (!interrupted) {
                synchronized (lock) {
                    currentTime = (int) seconds;
                }
                System.out.println("Sequential count: " + secondsToTimeFormat((int) seconds));
                
                spawnCounter += 0.1;
                if (spawnCounter >= timeSpawn && listEnemies.stream().mapToInt(Integer::intValue).sum() != 0) {
                    int enemyIndex = random.nextInt(2); //change with get enemis
                    boolean spawn = false;
                    while(!spawn){
                        if(listEnemies.get(enemyIndex) != 0) {
                            // Inserire qui il costruttore del nemico
                            spawn = true;
                        } else {
                            enemyIndex++;
                            enemyIndex = enemyIndex % 2;
                        }
                    }

                    spawnCounter -= timeSpawn;
                } else {
                    if (listEnemies.stream().mapToInt(Integer::intValue).sum() == 0) {//aggiungere isAlive
                        interrupted = true;
                    }
                }
                
                try {
                    Thread.sleep(100); // dormire per 0.1 secondi
                } catch (InterruptedException e) {
                    return;
                }
                seconds += 0.1;
            }
            interrupted = false;
            startCountdown();
        }

        private String secondsToTimeFormat(int totalSeconds) {
            int minutes = totalSeconds / 60;
            int seconds = totalSeconds % 60;
            return String.format("%02d:%02d", minutes, seconds);
        }
    }

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

    private String secondsToTimeFormat(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

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

        System.out.println("Game Over. All threads stopped.");
    }
}
