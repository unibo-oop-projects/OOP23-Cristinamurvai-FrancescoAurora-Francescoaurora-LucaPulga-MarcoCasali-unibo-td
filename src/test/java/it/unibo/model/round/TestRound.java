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

    @BeforeEach
    public void setUp() {
        round = new RoundImpl(5); // Initialize with 5 enemies for testing
    }

    /**
     * Test initial values after round creation. Verifies that the initial state
     * of a RoundImpl object is as expected.
     */
    @Test
    public void testInitialValues() {
        assertFalse(round.getLastRound());
        assertEquals(4.0, round.getTimeSpawn());
        assertEquals(0, round.getRoud());
        round.increaseRoud();
        assertEquals(4.0, round.getTimeSpawn());
        assertEquals(1, round.getRoud());
        List<Integer> enemiesSpawn = round.getEnemiesSpawn();
        assertEquals(5, enemiesSpawn.size());
        assertEquals(3, enemiesSpawn.stream().reduce(0, Integer::sum));
    }

    /**
     * Test default increaseRound behavior. Verifies that increaseRound method
     * increases round count and updates enemy spawns.
     */
    @Test
    public void testIncreaseRoundDefault() {
        round.increaseRoud(); // Round 1
        assertEquals(4.0, round.getTimeSpawn());
        List<Integer> enemiesSpawn = round.getEnemiesSpawn();
        assertEquals(5, enemiesSpawn.size());
        assertEquals(3, enemiesSpawn.get(0));
        round.increaseRoud(); // Round 2
        assertEquals(4.0, round.getTimeSpawn());
        enemiesSpawn = round.getEnemiesSpawn();
        assertEquals(5, enemiesSpawn.size());
        assertEquals(5, enemiesSpawn.get(0));
        assertEquals(0, enemiesSpawn.get(1));
        for (int i = 0; i < 10; i++) {// round 12
            round.increaseRoud();
        }
        enemiesSpawn = round.getEnemiesSpawn();
        assertEquals(5, enemiesSpawn.size());
        assertEquals(5, enemiesSpawn.get(0));
        assertEquals(5, enemiesSpawn.get(1));
        assertEquals(0, enemiesSpawn.get(2));
    }

    /**
     * Test behavior of increaseRound on boss round. Verifies that increaseRound
     * method correctly sets timeSpawn and adds boss enemies.
     */
    @Test
    public void testIncreaseRoundBossRound() {
        for (int i = 0; i < 29; i++) {
            round.increaseRoud();
        }
        round.increaseRoud(); // Round 30 (boss round)
        assertEquals(3.5, round.getTimeSpawn());
        List<Integer> enemiesSpawn = round.getEnemiesSpawn();
        assertEquals(5, enemiesSpawn.size());
        assertEquals(21, enemiesSpawn.get(0));
        assertEquals(21, enemiesSpawn.get(1));
        assertEquals(21, enemiesSpawn.get(2)); // Boss round adds enemies
        assertEquals(0, enemiesSpawn.get(3));
        assertEquals(0, enemiesSpawn.get(4));
    }

    /**
     * Test behavior of increaseRound when reaching last round. Verifies that
     * increaseRound method correctly identifies and handles the last round.
     */
    @Test
    public void testIncreaseRoundLastRound() {
        for (int i = 0; i < 68; i++) { // Simulate rounds until last round
            round.increaseRoud();
        }
        assertTrue(round.getLastRound());
        assertEquals(67, round.getRoud());
    }
}
