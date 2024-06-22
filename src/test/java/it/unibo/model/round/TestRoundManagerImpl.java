package it.unibo.model.round;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.entities.enemies.EnemiesManager;
import it.unibo.model.entities.enemies.EnemiesManagerImpl;

/**
 * Tests for {@link RoundManagerImpl}.
 */
public class TestRoundManagerImpl {

    private RoundManagerImpl roundManager;

    /**
     * Constructor for TestRoundManagerImpl.
     */
    public TestRoundManagerImpl() {
        // Initialize RoundManagerImpl with a mock EnemiesManager having 3 enemy types
        EnemiesManager mockEnemiesManager = new EnemiesManagerImpl();
        roundManager = new RoundManagerImpl(mockEnemiesManager);
    }

    /**
     * Sets up a new instance of {@link RoundManagerImpl} before each test.
     */
    @BeforeEach
    public void setUp() {
        // Initialize RoundManagerImpl with a mock EnemiesManager having 3 enemy types
        EnemiesManager mockEnemiesManager = new EnemiesManagerImpl();
        roundManager = new RoundManagerImpl(mockEnemiesManager);
    }

    /**
     * Test starting the game and checking the initial round number. Verifies
     * that the game starts correctly and the initial round is 1.
     */
    @Test
    public void testStartGameAndGetRound() {
        EnemiesManager mockEnemiesManager = new EnemiesManagerImpl();
        roundManager.startGame(mockEnemiesManager);
        assertEquals(1, roundManager.getRound());
    }
}
