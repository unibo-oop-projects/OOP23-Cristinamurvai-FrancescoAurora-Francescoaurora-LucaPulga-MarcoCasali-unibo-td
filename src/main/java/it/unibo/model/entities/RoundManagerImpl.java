package it.unibo.model.entities;



/**
 * Implements of interface Management of rouds
 */
public class RoundManagerImpl {
    

    private static final int ROUND_TIME = 30; // countdown time in seconds
    private Thread countdownThread;
    private Thread sequentialThread;
    private boolean interrupted = false;
    private RoundImp roudeGame;

    public RoundManagerImpl() {
        roudeGame = new RoundImp(2);//replace with get on enemis
    }

    public void startGameRound() {
        startCountdown();
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
        countdownThread = new Thread(new CountdownTask());
        countdownThread.start();
    }

    private class CountdownTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Countdown started");
            for (int i = ROUND_TIME; i > 0; i--) {
                if (interrupted) {
                    return;
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
        roudeGame.increaseRoud();
        sequentialThread = new Thread(new SequentialTask());
        sequentialThread.start();
    }

    private class SequentialTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Sequential counting started");
            int seconds = 0;
            while (!interrupted) {
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
}
