package it.unibo.model.entities.enemies;

import java.util.Map;

/**
 * Factory interface for creating enemy configurations.
 */
public interface EnemiesConfigFactory {

    /**
     * Loads enemy configurations from a JSON file.
     * 
     * @param file the path to the JSON file containing enemy configurations.
     */
    void fromJSONFile(String file);

    /**
     * Loads enemy configurations from a JSON string.
     * 
     * @param jsonString the JSON string containing enemy configurations.
     * @return a HashMap where the keys are enemy IDs and the values are EnemyConfig objects.
     */
    Map<Integer, EnemyConfig> fromJSON(String jsonString);

    /**
     * Enemy configurations.
     * 
     * @return a HashMap where the keys are enemy IDs and the values are EnemyConfig objects.
     */
    Map<Integer, EnemyConfig> getEnemiesConfig();
}
