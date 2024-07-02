package it.unibo.model.enemies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.entities.enemies.EnemiesManagerImpl;
import it.unibo.model.entities.enemies.EnemyState;
import it.unibo.model.map.GameMap;
import it.unibo.model.map.GameMapFactory;
import it.unibo.model.map.GameMapFactoryImpl;

/**
 * JUnit test class for {@link EnemiesManagerImpl}.
 * It tests various functionalities of the EnemiesManager implementation.
 */
public class TestEnemiesManagerImpl {

    private static final int ENEMY_RAT_ID = 0;
    private static final int ENEMY_GOBBY_ID = 1;
    private static final int ENEMY_KNIGHT_ID = 2;

    private static final String TEST_MAP = """
            {
                "rows": 20,
                "columns": 20,
                "filler": "grass",
                "tiles": [
            {
                "tile": "path_start",
                "positions": ["40"]
            },
            {
                "tile": "path_end",
                "positions": ["379"]
            },
            {
                "tile": "path_right",
                "positions": ["41-56","364-378"]
            },
            {
                "tile" : "path_down",
                "positions": ["57/237","244/344"]
            },
            {
                "tile" : "path_left",
                "positions": ["245-257"]
            },
            {
                "tile" : "buildslot",
                "positions": ["23","67","134","178","212","241","287","355","389"]
            }
        ]
    } """;

    private EnemiesManagerImpl enemiesManager;

    /**
     * Initializes an instance of {@link EnemiesManagerImpl}.
     */
    @BeforeEach
    void setUp() {
        enemiesManager = new EnemiesManagerImpl();
    }

    /**
     * Checks if enemies are correctly pushed into the game.
     */
    @Test
    public void testPushEnemy() {
        final GameMapFactory factory = new GameMapFactoryImpl();
        final GameMap map = factory.fromJSON(TEST_MAP);
        
        assertTrue(enemiesManager.getCurrentEnemies().isEmpty());
        enemiesManager.pushEnemy(ENEMY_RAT_ID);
        enemiesManager.pushEnemy(ENEMY_GOBBY_ID);
        enemiesManager.pushEnemy(ENEMY_KNIGHT_ID);
        enemiesManager.loadEnemiesInPushQueue();
        
        assertEquals(EnemyState.READY, enemy.getState());
        assertTrue(enemy.isAlive());
        assertEquals(100, enemy.getCurrentLP());
        assertEquals(2, enemy.getReward());
        assertEquals("src/main/resources/enemies/img/rat_move.gif", enemy.getImagePath());
    }
    
}
