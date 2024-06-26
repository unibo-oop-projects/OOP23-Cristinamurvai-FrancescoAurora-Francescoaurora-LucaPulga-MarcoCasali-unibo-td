package it.unibo.model.round;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test {@link RoundImpl}.
 */
public class TestRound {

    private RoundImpl round;
    private static final int ENEMIES_BOSS = 21;
    private static final int ENEMIES_SIZE = 5;
    private static final int ENEMIES_NOTE_SPAWN = 0;
    private static final int MAX_ROUND = 67;
    private static final Double TIME_SPAWN = 4.0;
    private static final Double TIME_SPAWN_BOSS = 3.5;
    private static final int ENEMIES_SPAWN_ROUND_2 = 5;
    private static final int ENEMIES_SPAWN_ROUND_1 = 3;
    private static final int ENEMIES_SPAWN_NO = 0;
    private static final int ROUND_1 = 1;
    private static final int LIVEL_BOSS_30 = 30;

    /**
     * Sets up a new instance of {@link RoundImpl} before each test.
     */
    @BeforeEach
    public void setUp() {
        round = new RoundImpl(ENEMIES_SIZE); // Initialize with 5 enemies for testing
    }

    /**
     * Test initial values after round creation. Verifies that the initial state
     * of a RoundImpl object is as expected.
     */
    @Test
    public void testInitialValues() {
        assertFalse(round.getLastRound());
        assertEquals(TIME_SPAWN, round.getTimeSpawn());
        assertEquals(0, round.getRoud());
        round.increaseRoud();
        assertEquals(TIME_SPAWN, round.getTimeSpawn());
        assertEquals(ROUND_1, round.getRoud());
        List<Integer> enemiesSpawn = round.getEnemiesSpawn();
        assertEquals(ENEMIES_SIZE, enemiesSpawn.size());
        assertEquals(ENEMIES_SPAWN_ROUND_1, enemiesSpawn.stream().reduce(0, Integer::sum));
    }

    /**
     * Test default increaseRound behavior. Verifies that increaseRound method
     * increases round count and updates enemy spawns.
     */
    @Test
    public void testIncreaseRoundDefault() {
        round.increaseRoud(); // Round 1
        assertEquals(TIME_SPAWN, round.getTimeSpawn());
        List<Integer> enemiesSpawn = round.getEnemiesSpawn();
        assertEquals(ENEMIES_SIZE, enemiesSpawn.size());
        assertEquals(ENEMIES_SPAWN_ROUND_1, enemiesSpawn.get(0));
        round.increaseRoud(); // Round 2
        assertEquals(TIME_SPAWN, round.getTimeSpawn());
        enemiesSpawn = round.getEnemiesSpawn();
        assertEquals(ENEMIES_SIZE, enemiesSpawn.size());
        assertEquals(ENEMIES_SPAWN_ROUND_2, enemiesSpawn.get(0));
        assertEquals(ENEMIES_SPAWN_NO, enemiesSpawn.get(1));
        for (int i = 0; i < 10; i++) { // Rounds 3 to 12
            round.increaseRoud();
        }
        enemiesSpawn = round.getEnemiesSpawn();
        assertEquals(ENEMIES_SIZE, enemiesSpawn.size());
        assertEquals(ENEMIES_SPAWN_ROUND_2, enemiesSpawn.get(0));
        assertEquals(ENEMIES_SPAWN_ROUND_2, enemiesSpawn.get(1));
        assertEquals(ENEMIES_SPAWN_NO, enemiesSpawn.get(2));
    }

    /**
     * Test behavior of increaseRound on boss round. Verifies that increaseRound
     * method correctly sets timeSpawn and adds boss enemies.
     */
    @Test
    public void testIncreaseRoundBossRound() {
        for (int i = 0; i < LIVEL_BOSS_30 - 1; i++) {
            round.increaseRoud();
        }
        round.increaseRoud(); // Round 30 (boss round)
        assertEquals(TIME_SPAWN_BOSS, round.getTimeSpawn());
        List<Integer> enemiesSpawn = round.getEnemiesSpawn();
        assertEquals(ENEMIES_SIZE, enemiesSpawn.size());
        assertEquals(ENEMIES_BOSS, enemiesSpawn.get(0));
        assertEquals(ENEMIES_BOSS, enemiesSpawn.get(1));
        assertEquals(ENEMIES_BOSS, enemiesSpawn.get(2)); // Boss round adds enemies
        assertEquals(ENEMIES_NOTE_SPAWN, enemiesSpawn.get(3));
        assertEquals(ENEMIES_NOTE_SPAWN, enemiesSpawn.get(4));
    }

    /**
     * Test behavior of increaseRound when reaching last round. Verifies that
     * increaseRound method correctly identifies and handles the last round.
     */
    @Test
    public void testIncreaseRoundLastRound() {
        for (int i = 0; i < MAX_ROUND + 1; i++) { // Simulate rounds until last round
            round.increaseRoud();
        }
        assertTrue(round.getLastRound());
        assertEquals(MAX_ROUND, round.getRoud());
    }
}
