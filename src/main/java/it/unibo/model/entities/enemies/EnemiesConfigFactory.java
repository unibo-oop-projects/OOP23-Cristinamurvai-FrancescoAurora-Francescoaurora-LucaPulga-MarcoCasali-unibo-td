package it.unibo.model.entities.enemies;

import java.util.HashMap;

public interface EnemiesConfigFactory {

    HashMap<Integer, EnemyConfig> fromJSONFile(final String file);

    HashMap<Integer, EnemyConfig> fromJSON(final String jsonString);

    HashMap<Integer, EnemyConfig> getEnemiesConfig();
}
