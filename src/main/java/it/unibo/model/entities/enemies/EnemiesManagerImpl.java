package it.unibo.model.entities.enemies;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.json.JSONObject;
import org.json.JSONArray;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

public class EnemiesManagerImpl implements EnemiesManager {

	private static final String FILE_PATH = "src/main/resources/enemies/json/level1.json";
	private final static double MAX_DISTANCE = 100;

	private ArrayList<Enemy> enemies;
	private ArrayList<Thread> enemiesThreads;

    public EnemiesManagerImpl() {
		this.enemies = new ArrayList<>();
		this.enemiesThreads = new ArrayList<>();
    }

	@Override
	public void parseEnemies() {
		try {
			//trasforma il file path in una stringa da cui posso leggere il file che farà da input alla libreria json
			String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
			JSONObject jsonObject = new JSONObject(content);
			JSONArray enemyTypesArray = jsonObject.getJSONArray("enemies");
			
			//uso la libreria json di java e zero problem
			for(int i=0; i<enemyTypesArray.length(); i++) {
				JSONObject enemyType = enemyTypesArray.getJSONObject(i);
				String name = enemyType.optString("name");
				String type = enemyType.getString("type");
				String imgPath = enemyType.getString("imgPath");
				Position2D position2d = new Position2D(enemyType.getJSONObject("position2d").getInt("x"), enemyType.getJSONObject("position2d").getInt("y"));
				Vector2D vector2d = new Vector2D(enemyType.getJSONObject("direction2d").getInt("x"), enemyType.getJSONObject("direction2d").getInt("y"));
				int lp = enemyType.getInt("lp");
				int reward = enemyType.getInt("reward");
				int quantity = enemyType.getInt("quantity");

				for(int j=0; j<quantity; j++) {
					buildEnemy(name, type, imgPath, position2d, vector2d, lp, reward);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void buildEnemy(String enemyName, String type, String imgPath, Position2D position2d, Vector2D vector2d, int lp, int reward) {
		EnemyImpl newEnemy = new EnemyImpl(this.enemies.size(), enemyName, type, imgPath, position2d, vector2d, lp, reward);
		Thread newEnemyThread = new Thread(newEnemy);

		newEnemyThread.start();

		this.enemies.add(newEnemy);
		this.enemiesThreads.add(newEnemyThread);
	}

    @Override
	public Set<Enemy> getCurrentEnemies() {
        return this.enemies.stream().collect(Collectors.toSet());
	}
    
	@Override
	public Optional<Enemy> getNearestEnemy(Position2D position2d, int radius) {
		Optional<Enemy>  nearestEnemy = Optional.empty();
		double nearestDistance = MAX_DISTANCE;
		double distance;
		for (Enemy enemy : this.enemies) {
			distance = Math.sqrt(Math.pow(position2d.x() - enemy.getPosition().x(), 2) + Math.pow(position2d.y() - enemy.getPosition().y(), 2));
			if(distance < nearestDistance && distance <= radius) {
				nearestDistance = distance;
				nearestEnemy = Optional.of(enemy);
			}
		}
		return nearestEnemy;
	}
	
}
