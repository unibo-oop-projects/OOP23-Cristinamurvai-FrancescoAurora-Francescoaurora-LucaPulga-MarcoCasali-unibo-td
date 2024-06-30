package it.unibo.model.entities.enemies;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import it.unibo.model.core.GameState;
import it.unibo.model.map.GameMap;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class EnemiesManagerImpl implements EnemiesManager {

    private final EnemiesConfigFactoryImpl enemiesConfigFactory;
    private final List<Enemy> enemies;

    private Optional<GameMap> gameMap;

    private static final double ENEMY_SPEED_SCALER = 0.05;

    private boolean pause;

    private int playerReward;
    private int playerLivesLost;

    public EnemiesManagerImpl() {
        this.enemiesConfigFactory = new EnemiesConfigFactoryImpl();
        this.enemies = new ArrayList<>();//new CopyOnWriteArrayList();//new ArrayList<>();
        this.gameMap = Optional.empty();
        this.pause = false;
        this.playerReward = 0;
        this.playerLivesLost = 0;
    }

    /*
    metodo pushEnemy(prende in ingresso un intero: 0, 1, 2 in base alla tipologia di nemico
     */
    @Override
    public void pushEnemy(int id) {
        final String name = this.enemiesConfigFactory.getEnemiesConfig().get(id).getEnemyName();
        final String type = this.enemiesConfigFactory.getEnemiesConfig().get(id).getEnemyType();
        final String imgPath = this.enemiesConfigFactory.getEnemiesConfig().get(id).getEnemyImgPath();
        final int lp = this.enemiesConfigFactory.getEnemiesConfig().get(id).getLp();
        final int reward = this.enemiesConfigFactory.getEnemiesConfig().get(id).getReward();

        buildEnemy(this.gameMap.get(), name, type, imgPath, lp, reward);
    }

    @Override
    //  Used only to test enemies spawn
    public void parseEnemies() {
        for (Integer key : this.enemiesConfigFactory.getEnemiesConfig().keySet()) {
            for (int i = 0; i < this.enemiesConfigFactory.getEnemiesConfig().get(key).getQuantity(); i++) {
                pushEnemy(key);
            }
        }
    }

    @Override
    public long getEnemiesAlive(ArrayList<Enemy> enemies) {
        return this.enemies.stream().filter(enemy -> enemy.getState().equals(EnemyState.MOVING)).count();
    }

    @Override
	public int getNumberOfPlayerLivesLost() {
		return this.playerLivesLost;
	}

    private int computeNumberOfPlayerLivesLost() {
        final int livesLost = (int) this.enemies.stream().filter(enemy -> enemy.getState().equals(EnemyState.FINISHED)).count();
        this.enemies.stream()
                    .filter(enemy -> enemy.getState().equals(EnemyState.FINISHED))
                    .forEach(enemy -> enemy.deactivate());
		return livesLost;
    }

    @Override
    public int getPLayerReward() {
		return this.playerReward;
	}

    private int computePlayerReward() {
        final int playerReward = this.enemies.stream().filter(enemy -> enemy.getState().equals(EnemyState.DEAD))
									.mapToInt(Enemy::getReward)
									.sum();
        this.enemies.stream()
                    .filter(enemy -> enemy.getState().equals(EnemyState.DEAD))
                    .forEach(enemy -> enemy.deactivate());
		return playerReward;
    }

    @Override
    public void buildEnemy(final GameMap gameMap, final String enemyName, final String type, final String imgPath,
            final int lp, final int reward) {
        Position2D spawnPosition = gameMap.getSpawnPosition();
        Vector2D direction = gameMap.getPathDirection(spawnPosition);
        Position2D pathEndPosition = gameMap.getPathEndPosition();
        EnemyImpl newEnemy = new EnemyImpl(this.enemies.size(), enemyName, type, imgPath, spawnPosition,
                direction, pathEndPosition, lp, reward);

        this.enemies.add(newEnemy);
    }

    @Override
    public Set<Enemy> getCurrentEnemies() {
        return Set.copyOf(this.enemies.stream().filter(e -> e.isAlive()).collect(Collectors.toSet()));
    }

    @Override
    public void setMap(final GameMap gameMap) {
        this.gameMap = Optional.of(gameMap);
    }

    @Override
    public int getNEnemyTypes() {
        return this.enemiesConfigFactory.getNEnemyTypes();
    }

    @Override
    public void togglePause() {
        if (this.pause) {
            this.enemies.stream().filter(e -> e.getState().equals(EnemyState.PAUSED)).forEach(e -> e.resume());
        } else {
            this.enemies.stream().filter(e -> e.getState().equals(EnemyState.MOVING)).forEach(e -> e.pause());
        }
        this.pause = !this.pause;
    }

    @Override
    public void update(GameState gameState) {
        this.playerLivesLost = this.computeNumberOfPlayerLivesLost();
        this.playerReward = this.computePlayerReward();
        
        for (Enemy enemy : gameState.getEnemies()) {
            if (enemy.getState().equals(EnemyState.READY)) {
                enemy.startMoving();
            }
            if (!(enemy.getPosition().xInt() == this.gameMap.get().getPathEndPosition().xInt() && enemy.getPosition().yInt() == this.gameMap.get().getPathEndPosition().yInt())) {
                //System.out.println("Cosa mi fa esplodere? " + enemy.getId() + " " + enemy.getPosition().x() + " " + enemy.getPosition().y());
                enemy.setDirection(this.gameMap.get().getPathDirection(new Position2D(enemy.getPosition().xInt(), enemy.getPosition().yInt())).scale(ENEMY_SPEED_SCALER));
            }
            enemy.move();
        }
    }
}
