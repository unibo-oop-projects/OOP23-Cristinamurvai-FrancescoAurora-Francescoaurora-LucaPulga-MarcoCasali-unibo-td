package it.unibo.model.entities.enemies;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.model.utilities.Position2D;

public class EnemiesManagerImpl implements EnemiesManager {

	private final static String TYPE = "enemy";
	private final static int LP = 100;
	private final static int REWARD = 10;
	private final static double MAX_DISTANCE = 100;

	private ArrayList<Enemy> enemies;
	private ArrayList<Thread> enemiesThreads;

    public EnemiesManagerImpl() {
		this.enemies = new ArrayList<>();
		this.enemiesThreads = new ArrayList<>();
    }

	@Override
	public void buildEnemy(String enemyName) {
		EnemyImpl newEnemy = new EnemyImpl(this.enemies.size(), enemyName, TYPE, "", null, null, LP, REWARD);
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
