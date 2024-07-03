package it.unibo.model.entities.enemies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the EnemiesConfigFactory interface.
 * Provides methods to load enemy configurations from JSON files or strings.
 */
public class EnemiesConfigFactoryImpl implements EnemiesConfigFactory {

    private final Logger logger = LoggerFactory.getLogger(EnemiesConfigFactoryImpl.class);
    private static final String ENEMIES = "enemies";
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final String IMG_PATH = "imgPath";
    private static final String LP = "lp";
    private static final String REWARD = "reward";
    private static final String QUANTITY = "quantity";

    private Map<Integer, EnemyConfig> enemiesConfig;
    private int nEnemyTypes;

    /**
     * Constructor.
     */
    public EnemiesConfigFactoryImpl() {
        this.enemiesConfig = new HashMap<>();
    }

    /**
     * Loads enemy configurations from a JSON file.
     * 
     * @param file the path to the JSON file containing enemy configurations.
     */
    @Override
    public void fromJSONFile(final String file) {
        String fileContent;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream(file), StandardCharsets.UTF_8))) {
            fileContent = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            fileContent = "";
            logger.error("Error when retrieving file: {}\n", file, e);
        }
        this.enemiesConfig = fromJSON(fileContent);
    }

    /**
     * Loads enemy configurations from a JSON string.
     * 
     * @param jsonString the JSON string containing enemy configurations.
     * @return a HashMap where the keys are enemy IDs and the values are EnemyConfig objects.
     */
    @Override
    public HashMap<Integer, EnemyConfig> fromJSON(final String jsonString) {
        final JSONObject source = new JSONObject(jsonString);
        final JSONArray enemiesArray = source.getJSONArray(ENEMIES);

        final HashMap<Integer, EnemyConfig> enemiesConfig = new HashMap<>();

        this.nEnemyTypes = enemiesArray.length();

        for (int i = 0; i < enemiesArray.length(); i++) {
            final JSONObject jObj = enemiesArray.getJSONObject(i);
            enemiesConfig.put(i, buildEnemyConfig(jObj.getString(NAME),
                    jObj.getString(TYPE),
                    jObj.getString(IMG_PATH),
                    jObj.getInt(LP),
                    jObj.getInt(REWARD),
                    jObj.getInt(QUANTITY)));
        }
        return enemiesConfig;
    }

    /**
     * Builds an EnemyConfig object.
     * 
     * @param name the name of the enemy.
     * @param type the type of the enemy.
     * @param imgPath the image path of the enemy.
     * @param lp the life points of the enemy.
     * @param reward the reward for defeating the enemy.
     * @param quantity the quantity of this type of enemy.
     * @return an EnemyConfig object.
     */
    private EnemyConfig buildEnemyConfig(final String name, final String type, 
                                         final String imgPath, final int lp, 
                                         final int reward, final int quantity) {
        return new EnemyConfig() {
            private final String enemyName = name;
            private final String enemyType = type;
            private final String enemyImgPath = imgPath;
            private final int startingEnemyLp = lp;
            private final int enemyReward = reward;
            private final int enemyQuantity = quantity;

            @Override
            public String getEnemyName() {
                return this.enemyName;
            }

            @Override
            public String getEnemyType() {
                return this.enemyType;
            }

            @Override
            public String getEnemyImgPath() {
                return this.enemyImgPath;
            }

            @Override
            public int getLp() {
                return this.startingEnemyLp;
            }

            @Override
            public int getReward() {
                return this.enemyReward;
            }

            @Override
            public int getQuantity() {
                return this.enemyQuantity;
            }

        };
    }

    /**
     * Get the enemy configurations.
     * 
     * @return a HashMap where the keys are enemy IDs and the values are EnemyConfig objects.
     */
    @Override
    public HashMap<Integer, EnemyConfig> getEnemiesConfig() {
        return new HashMap<Integer, EnemyConfig>(this.enemiesConfig);
    }

    /**
     * Retrieves the number of different enemy types loaded.
     * 
     * @return the number of enemy types.
     */
    public int getNEnemyTypes() {
        return this.nEnemyTypes;
    }

}
