package it.unibo.model.enemies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.entities.enemies.EnemiesConfigFactoryImpl;

/**
 * JUnit test class for {@link EnemiesConfigFactoryImpl}.
 * It verifies the functionality of loading enemy configurations from JSON files and strings.
 */
public class TestEnemiesConfigFactoryImpl {
    
    private static final int FIRST_ENEMY_TYPE = 0;
    private static final int SECOND_ENEMY_TYPE = 1;
    private static final int THIRD_ENEMY_TYPE = 2;
    
    private EnemiesConfigFactoryImpl enemiesConfigFactory;

    /**
     * Initializes an instance of {@link EnemiesConfigFactoryImpl}.
     */
    @BeforeEach
    void setUp() {
        enemiesConfigFactory = new EnemiesConfigFactoryImpl();
    }

    /**
     * Test method to verify loading enemy configurations from a JSON file.
     */
    @Test
    void testFromJSONFile() {
        Assertions.assertNotNull(enemiesConfigFactory.getEnemiesConfig());
        Assertions.assertEquals("rat", enemiesConfigFactory.getEnemiesConfig().get(FIRST_ENEMY_TYPE).getEnemyName());
        Assertions.assertEquals("gobby", enemiesConfigFactory.getEnemiesConfig().get(SECOND_ENEMY_TYPE).getEnemyName());
        Assertions.assertEquals("knight", enemiesConfigFactory.getEnemiesConfig().get(THIRD_ENEMY_TYPE).getEnemyName());
    }
    
}
