package it.unibo.model.entities.enemies;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.model.map.GameMap;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

public class EnemiesManagerImpl implements EnemiesManager {

    private final static long ENEMIES_MAP_ENTERING_SEPARATION = 1000;

    private final EnemiesConfigFactoryImpl enemiesConfigFactory;
    private final ArrayList<Enemy> enemies;
    private final ArrayList<Thread> enemiesThreads;

    private Optional<GameMap> gameMap;
    private long lastNewEnemyStartingTime;
    private static final int DAMAGE_DIVIDER = 50;

    public EnemiesManagerImpl() {
        this.enemiesConfigFactory = new EnemiesConfigFactoryImpl();
        this.enemies = new ArrayList<>();
        this.enemiesThreads = new ArrayList<>();
        this.gameMap = Optional.empty();
        this.lastNewEnemyStartingTime = 0;
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
    public List<Integer> getDamageAndRewardsFromFinishedEnemies() {
        int damage = 0;
        int rewards = 0;
        for (Enemy enemy : enemies) {
            if (enemy.getState().equals(EnemyState.DEAD)) {
                rewards += enemy.getReward();
                enemy.deactivate();
            }
            if (enemy.getState().equals(EnemyState.FINISHED)) {
                damage += enemy.getDamage(0);
                enemy.deactivate();
            }
        }
        for (int i = enemies.size() - 1; i >= 0; i--) {
            if (enemies.get(i).getState().equals(EnemyState.FINISHED)) {
                enemies.remove(i);
            }
        }
        List<Integer> damageAndRewards = new ArrayList<>();
        damageAndRewards.add((int) damage / DAMAGE_DIVIDER);
        damageAndRewards.add(rewards);
        return damageAndRewards;
    }

    @Override
    public void buildEnemy(final GameMap gameMap, final String enemyName, final String type, final String imgPath,
            final int lp, final int reward) {
        Position2D spawnPosition = gameMap.getSpawnPosition();
        Vector2D direction = gameMap.getPathDirection(spawnPosition);
        Position2D pathEndPosition = gameMap.getPathEndPosition();
        EnemyImpl newEnemy = new EnemyImpl(this.enemies.size(), enemyName, type, imgPath, spawnPosition,
                direction, pathEndPosition, lp, reward);
        Thread newEnemyThread = new Thread(newEnemy);

        newEnemyThread.start();

        this.enemies.add(newEnemy);
        this.enemiesThreads.add(newEnemyThread);

    }

    @Override
    public Set<Enemy> getCurrentEnemies() {
        //return this.enemies.stream().collect(Collectors.toSet());
        return this.enemies.stream().filter(e -> e.isAlive()).collect(Collectors.toSet());
    }

    @Override
    public void setMap(final GameMap gameMap) {
        this.gameMap = Optional.of(gameMap);
    }

    @Override
    // TO-DO: raise exception if the optional map is empty
    public void updateEnemiesDirections(final long currentTimeMillis) {
        boolean newEnemyEntered = false;
        for (Enemy enemy : enemies) {
            if (enemy.getState().equals(EnemyState.READY) && !newEnemyEntered
                    && (currentTimeMillis - this.lastNewEnemyStartingTime) >= ENEMIES_MAP_ENTERING_SEPARATION) {
                enemy.startMoving();
                newEnemyEntered = true;
                this.lastNewEnemyStartingTime = currentTimeMillis;
            }
            if (!(enemy.getPosition().xInt() >= this.gameMap.get().getPathEndPosition().xInt() && enemy.getPosition().yInt() >= this.gameMap.get().getPathEndPosition().yInt())) {
                enemy.setDirection(this.gameMap.get().getPathDirection(enemy.getPosition()));
            }
        }
    }

    @Override
    public int getNEnemyTypes() {
        return this.enemiesConfigFactory.getNEnemyTypes();
    }
}
