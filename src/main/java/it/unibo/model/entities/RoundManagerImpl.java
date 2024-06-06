package it.unibo.model.entities;

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

    public RoundManagerImpl(final EnemiesManagerImpl enemiesManager) {
        enemies = enemiesManager;
        round = new RoundImp(2); //change with get 
    }


    private void startCountdown() {
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
        round.increaseRoud();
        sequentialThread = new Thread(new SequentialTask());
        sequentialThread.start();
    }

    private class SequentialTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Sequential counting started");
            int seconds = 0;
            while (!interrupted) {
                synchronized (lock) {
                    currentTime = seconds;
                }
                System.out.println("Sequential count: " + secondsToTimeFormat(seconds));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
                seconds++;
            }
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
}
