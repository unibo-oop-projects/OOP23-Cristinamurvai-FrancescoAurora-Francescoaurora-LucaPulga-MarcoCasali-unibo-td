package it.unibo.model.enemies;

import it.unibo.model.entities.enemies.EnemyImpl;
import it.unibo.model.entities.enemies.EnemyState;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


/**
 * JUnit test class for {@link EnemyImpl}.
 * It tests various functionalities of the Enemy implementation class.
 */
class TestEnemyImpl {

    private static final int ENEMY_ID = 0;
    private static final int ENEMY_STARTING_LP = 100;
    private static final int ENEMY_REWARD = 2;
    private static final int ENEMY_INITIAL_X = 0;
    private static final int ENEMY_INITIAL_Y = 0;
    private static final int ENEMY_DIRECTION_X = 1;
    private static final int ENEMY_DIRECTION_Y = 0;
    private static final int ENEMY_END_PATH_X = 2;
    private static final int ENEMY_END_PATH_Y = 0;
    private static final int DAMAGE_TO_ENEMY_20 = 20;
    private static final int DAMAGE_TO_ENEMY_80 = 80;

    private static final int TEST_ENEMY_STARTING_LP = 100;
    private static final int TEST_ENEMY_REWARD = 2;
    private static final int TEST_ENEMY_REMAINING_LP_80 = 80;
    private static final int TEST_ENEMY_REMAINING_LP_0 = 0;
    private static final double TEST_AFTER_MOVE_POS_X = 1.0;
    private static final double TEST_AFTER_MOVE_POS_Y = 0.0;

    private EnemyImpl enemy;

    /**
     * Initializes an instance of {@link EnemyImpl} with predefined parameters.
     */
    @BeforeEach
    void setUp() {
        final Position2D initialPosition = new Position2D(ENEMY_INITIAL_X, ENEMY_INITIAL_Y);
        final Vector2D initialDirection = new Vector2D(ENEMY_DIRECTION_X, ENEMY_DIRECTION_Y);
        final Position2D pathEndPosition = new Position2D(ENEMY_END_PATH_X, ENEMY_END_PATH_Y);

        enemy = new EnemyImpl(ENEMY_ID, "rat", "base", "src/main/resources/enemies/img/rat_move.gif",
                initialPosition, initialDirection, pathEndPosition, ENEMY_STARTING_LP, ENEMY_REWARD);
    }

    /**
     * Checks if initial state, life points, reward, and image path are correctly set.
     */
    @Test
    void testInitialState() {
        assertEquals(EnemyState.READY, enemy.getState());
        assertTrue(enemy.isAlive());
        assertEquals(TEST_ENEMY_STARTING_LP, enemy.getCurrentLP());
        assertEquals(TEST_ENEMY_REWARD, enemy.getReward());
        assertEquals("src/main/resources/enemies/img/rat_move.gif", enemy.getImagePath());
    }

    /**
     * Checks if the enemy's life points decrease correctly
     * and its state updates when dead.
     */
    @Test
    void testGetDamage() {
        int remainingLP = enemy.getDamage(DAMAGE_TO_ENEMY_20);
        assertEquals(TEST_ENEMY_REMAINING_LP_80, remainingLP);
        assertTrue(enemy.isAlive());
        remainingLP = enemy.getDamage(DAMAGE_TO_ENEMY_80);
        assertEquals(TEST_ENEMY_REMAINING_LP_0, remainingLP);
        assertFalse(enemy.isAlive());
        assertEquals(EnemyState.DEAD, enemy.getState());
    }

    /**
     * Checks if the enemy moves along its current direction 
     * and updates its state upon reaching the path end.
     */
    @Test
    void testMove() {
        enemy.startMoving();
        enemy.move();
        assertEquals(TEST_AFTER_MOVE_POS_X, enemy.getPosition().x());
        assertEquals(TEST_AFTER_MOVE_POS_Y, enemy.getPosition().y());
        enemy.move();
        assertEquals(EnemyState.FINISHED, enemy.getState());
        assertFalse(enemy.isAlive());
    }

    /**
     * Checks if the enemy's state changes to INACTIVE upon deactivation.
     */
    @Test
    void testDeactivate() {
        enemy.deactivate();
        assertEquals(EnemyState.INACTIVE, enemy.getState());
    }

    /**
     * Checks if the enemy's state changes to PAUSED 
     * upon pausing and back to MOVING upon resuming.
     */
    @Test
    void testPauseAndResume() {
        enemy.pause();
        assertEquals(EnemyState.PAUSED, enemy.getState());
        enemy.resume();
        assertEquals(EnemyState.MOVING, enemy.getState());
    }
}
