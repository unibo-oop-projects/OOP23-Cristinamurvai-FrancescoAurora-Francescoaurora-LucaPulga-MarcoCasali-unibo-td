package it.unibo.model.entities.enemies;

import java.util.HashMap;

/**
 * Factory interface for creating enemy configurations.
 */
public interface EnemiesConfigFactory {

    /**
     * Loads enemy configurations from a JSON file.
     * 
     * @param file the path to the JSON file containing enemy configurations.
     * @return a HashMap where the keys are enemy IDs and the values are EnemyConfig objects.
     */
    HashMap<Integer, EnemyConfig> fromJSONFile(final String file);

    /**
     * Loads enemy configurations from a JSON string.
     * 
     * @param jsonString the JSON string containing enemy configurations.
     * @return a HashMap where the keys are enemy IDs and the values are EnemyConfig objects.
     */
    HashMap<Integer, EnemyConfig> fromJSON(final String jsonString);

    /**
     * Enemy configurations.
     * 
     * @return a HashMap where the keys are enemy IDs and the values are EnemyConfig objects.
     */
    HashMap<Integer, EnemyConfig> getEnemiesConfig();
}
