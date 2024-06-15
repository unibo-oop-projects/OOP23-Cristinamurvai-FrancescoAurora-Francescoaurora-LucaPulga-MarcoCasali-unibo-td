package it.unibo.model.round;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements for roud manager.
 */
public class RoundImp implements Round {
    private int numberEnemies;
    private double timeSpawn;
    private static final double DEFAULT_TIME_SPAWN = 4;
    private static final double MIN_TIME_SPAWN = 0.2;
    private static final int DEFAULT_NUMBER_ENEMIES = 3;
    private int rounds;
    private List<Integer> enemiesSpawn;
    private static final int BOSS_ROUND = 9;
    private static final double TIME_BOSS_ROUND = 3.5;
    private boolean lastRound = false;

    /**
     * Constructor.
     * @param enemies number of enemies 
     */
    public RoundImp(final int enemies) {
        numberEnemies = enemies; //get enemis
        timeSpawn = DEFAULT_TIME_SPAWN;
        rounds = -1;
        this.enemiesSpawn = new ArrayList<>();
        for (int i = 0; i < numberEnemies; i++) {
            this.enemiesSpawn.add(0);
        }
    }

    @Override
    public void increaseRoud() {
        rounds++;
        int tmp = rounds / 10;
        //I can increase by a constant value determined by the level/something, e.g. +2 per active type
        //if the type ends then I activate a multiplier which first has a value of 1 so instead of adding 2 it adds 4 then 8 etc.
        if (rounds < numberEnemies * 10) {
            if (rounds % 10 == 0) {
                for (int i = 0; i <= tmp; i++) {
                    enemiesSpawn.set(i, DEFAULT_NUMBER_ENEMIES);
                }
                timeSpawn = DEFAULT_TIME_SPAWN;
            } else {
                for (int i = 0; i <= tmp; i++) {
                    enemiesSpawn.set(i, enemiesSpawn.get(i) + 2);
                }
                if (rounds % 10 == BOSS_ROUND) {
                    timeSpawn = TIME_BOSS_ROUND;
                }
            }
        } else {
            if (timeSpawn > MIN_TIME_SPAWN) {
                timeSpawn -= MIN_TIME_SPAWN;
            } else {
                lastRound = true;
                rounds += -1;
            }
        }
    }

    @Override
    public double getTimeSpawn() {
        return timeSpawn;
    }

    @Override
    public List<Integer> getEnemiesSpawn() {
        return new ArrayList<>(enemiesSpawn);
    }

    @Override
    public int getRoud() {
        return rounds + 1;
    }

    @Override
    public boolean getLastRound() {
        return lastRound;
    }
}