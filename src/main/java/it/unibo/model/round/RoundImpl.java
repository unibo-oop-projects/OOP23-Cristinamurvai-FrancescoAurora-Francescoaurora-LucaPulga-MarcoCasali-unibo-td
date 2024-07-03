package it.unibo.model.round;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements for roud manager.
 */
public class RoundImpl implements Round {

    private final int numberEnemies;
    private double timeSpawn;
    private static final double DEFAULT_TIME_SPAWN = 4;
    private static final double MIN_TIME_SPAWN = 0.2;
    private static final int DEFAULT_NUMBER_ENEMIES = 3;
    private int rounds;
    private final List<Integer> enemiesSpawn;
    private static final int BOSS_ROUND = 4;
    private static final int ROUND = 5;
    private static final double TIME_BOSS_ROUND = 3.5;
    private boolean lastRound = false;

    /**
     * Constructor.
     *
     * @param enemies number of enemies
     */
    public RoundImpl(final int enemies) {
        numberEnemies = enemies; //get enemis
        timeSpawn = DEFAULT_TIME_SPAWN;
        rounds = -1;
        this.enemiesSpawn = new ArrayList<>();
        for (int i = 0; i < numberEnemies; i++) {
            this.enemiesSpawn.add(0);
        }
    }

    @Override
    public final void increaseRoud() {
        rounds++;
        final int tmp = rounds / ROUND;
        //I can increase by a constant value determined by the level/something, e.g. +2 per active type
        //if the type ends then I activate a multiplier which first has a value of 1 so instead of adding 2 it adds 4 then 8 etc.
        if (rounds < numberEnemies * ROUND) {
            if (rounds % ROUND == 0) {
                for (int i = 0; i <= tmp; i++) {
                    enemiesSpawn.set(i, DEFAULT_NUMBER_ENEMIES);
                }
                timeSpawn = DEFAULT_TIME_SPAWN;
            } else {
                for (int i = 0; i <= tmp; i++) {
                    enemiesSpawn.set(i, enemiesSpawn.get(i) + 2);
                }
                if (rounds % ROUND == BOSS_ROUND) {
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
    public final double getTimeSpawn() {
        return timeSpawn;
    }

    @Override
    public final List<Integer> getEnemiesSpawn() {
        return new ArrayList<>(enemiesSpawn);
    }

    @Override
    public final int getRoud() {
        return rounds + 1;
    }

    @Override
    public final boolean isLastRound() {
        return lastRound;
    }
}
