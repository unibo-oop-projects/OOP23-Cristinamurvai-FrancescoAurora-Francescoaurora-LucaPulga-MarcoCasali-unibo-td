package it.unibo.model.entities.enemies;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

public class EnemiesConfigFactoryImpl implements EnemiesConfigFactory {
    private static final String FILE_PATH = "enemies/json/level1.json";
    private static final String ENEMIES = "enemies";
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final String IMG_PATH = "imgPath";
    private static final String LP = "lp";
    private static final String REWARD = "reward";
    private static final String QUANTITY = "quantity";
    
    private HashMap<Integer, EnemyConfig> enemiesConfig;
    private int nEnemyTypes;

    public EnemiesConfigFactoryImpl() {
        this.enemiesConfig = fromJSONFile(FILE_PATH);
    }

    public HashMap<Integer, EnemyConfig> fromJSONFile(final String file) {
        String fileContent = null;
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(ClassLoader.getSystemResourceAsStream(file)))) {
            fileContent = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.err.println("Error when retrieving json file for enemies: " + file);
        }
        return fromJSON(fileContent);
    }

	public HashMap<Integer, EnemyConfig> fromJSON(final String jsonString) {
        final JSONObject source = new JSONObject(jsonString);
        final JSONArray enemiesArray = source.getJSONArray(ENEMIES);

        HashMap<Integer, EnemyConfig> enemiesConfig = new HashMap<>();

        this.nEnemyTypes = enemiesArray.length();

        for(int i = 0; i < enemiesArray.length(); i++) {
            JSONObject jObj = enemiesArray.getJSONObject(i);
            enemiesConfig.put(i, buildEnemyConfig(jObj.getString(NAME), 
                                                  jObj.getString(TYPE), 
                                                  jObj.getString(IMG_PATH),
                                                  jObj.getInt(LP), 
                                                  jObj.getInt(REWARD),
                                                  jObj.getInt(QUANTITY)));
        }
        return enemiesConfig;
    }

	private EnemyConfig buildEnemyConfig(final String name, final String type, final String imgPath, final int lp, final int reward, final int quantity) {
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

    public HashMap<Integer, EnemyConfig> getEnemiesConfig() {
        return this.enemiesConfig;
    }

    public int getNEnemyTypes() {
        return this.nEnemyTypes;
    }
    
}
