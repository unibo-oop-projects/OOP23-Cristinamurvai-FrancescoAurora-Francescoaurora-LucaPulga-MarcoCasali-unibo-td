package it.unibo.model.round;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import it.unibo.model.entities.enemies.EnemiesManager;
import it.unibo.model.entities.enemies.EnemiesManagerImpl;

/**
 * Tests for {@link RoundManagerImpl}.
 */
class TestRoundManagerImpl {

    private final EnemiesManager mockEnemiesManager = new EnemiesManagerImpl();
    private final RoundManagerImpl roundManager = new RoundManagerImpl(mockEnemiesManager);

    /**
     * Test starting the game and checking the initial round number. Verifies
     * that the game starts correctly and the initial round is 1.
     */
    @Test
    void testStartGameAndGetRound() {
        roundManager.startGame(mockEnemiesManager); // Start the game with mockEnemiesManager
        assertEquals(1, roundManager.getRound());
    }
}
