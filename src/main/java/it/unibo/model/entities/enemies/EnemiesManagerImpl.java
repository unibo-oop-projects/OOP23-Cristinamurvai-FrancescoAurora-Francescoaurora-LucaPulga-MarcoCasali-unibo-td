package it.unibo.model.entities.enemies;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import it.unibo.model.core.GameState;
import it.unibo.model.map.GameMap;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Implementation of the EnemiesManager interface.
 * Manages enemies in the game, handles spawning, updating, and managing enemy entities.
 */
public class EnemiesManagerImpl implements EnemiesManager {

    private static final double ENEMY_SPEED_SCALER = 0.05;

    private final EnemiesConfigFactoryImpl enemiesConfigFactory;
    private final List<Enemy> enemies;
    private final List<Enemy> enemiesToPush;
    private Optional<GameMap> gameMap;
    private boolean pause;
    private boolean noRunningEnemies;
    private int playerReward;
    private int playerLivesLost;

    /**
     * Constructs an instance of EnemiesManagerImpl.
     */
    public EnemiesManagerImpl() {
        this.enemiesConfigFactory = new EnemiesConfigFactoryImpl();
        this.enemies = new ArrayList<>();
        this.enemiesToPush = new ArrayList<>();
        this.gameMap = Optional.empty();
        this.pause = false;
        this.noRunningEnemies = true;
        this.playerReward = 0;
        this.playerLivesLost = 0;
    }

    /**
     * Pushes an enemy of specified type into the game.
     *
     * @param id The identifier of the enemy type to push.
     */
    @Override
    public void pushEnemy(final int id) {
        final String name = this.enemiesConfigFactory.getEnemiesConfig().get(id).getEnemyName();
        final String type = this.enemiesConfigFactory.getEnemiesConfig().get(id).getEnemyType();
        final String imgPath = this.enemiesConfigFactory.getEnemiesConfig().get(id).getEnemyImgPath();
        final int lp = this.enemiesConfigFactory.getEnemiesConfig().get(id).getLp();
        final int reward = this.enemiesConfigFactory.getEnemiesConfig().get(id).getReward();

        buildEnemy(this.gameMap.get(), name, type, imgPath, lp, reward);
    }

     /**
     * Parses enemies configuration and spawns them in the game.
     * Used primarily for testing enemy spawning.
     */
    @Override
    public void parseEnemies() {
        for (Integer key : this.enemiesConfigFactory.getEnemiesConfig().keySet()) {
            for (int i = 0; i < this.enemiesConfigFactory.getEnemiesConfig().get(key).getQuantity(); i++) {
                pushEnemy(key);
            }
        }
    }

    /**
     * Retrieves the number of enemies currently alive and moving.
     *
     * @param enemies The list of enemies to check.
     * @return The number of enemies alive and moving.
     */
    @Override
    public long getEnemiesAlive(final ArrayList<Enemy> enemies) {
        return this.enemies.stream().filter(enemy -> enemy.getState().equals(EnemyState.MOVING)).count();
    }

    /**
     * Retrieves the total number of player lives lost in the game.
     *
     * @return The number of player lives lost.
     */
    @Override
    public int getNumberOfPlayerLivesLost() {
        return this.playerLivesLost;
    }

    /**
     * Computes and updates the number of player lives lost based on enemies that have finished their path.
     *
     * @return The computed number of player lives lost.
     */
    private int computeNumberOfPlayerLivesLost() {
        final int livesLost = (int) this.enemies.stream().filter(enemy -> enemy.getState().equals(EnemyState.FINISHED)).count();
        this.enemies.stream()
                    .filter(enemy -> enemy.getState().equals(EnemyState.FINISHED))
                    .forEach(enemy -> enemy.deactivate());
        return livesLost;
    }

    /**
     * Retrieves the total reward accumulated by the player.
     *
     * @return The total reward accumulated.
     */
    @Override
    public int getPLayerReward() {
        return this.playerReward;
    }

    /**
     * Computes and updates the total reward accumulated by the player based on defeated enemies.
     *
     * @return The computed total reward.
     */
    private int computePlayerReward() {
        final int playerReward = this.enemies.stream().filter(enemy -> enemy.getState().equals(EnemyState.DEAD))
                                .mapToInt(Enemy::getReward)
                                .sum();
        this.enemies.stream()
                    .filter(enemy -> enemy.getState().equals(EnemyState.DEAD))
                    .forEach(enemy -> enemy.deactivate());
        return playerReward;
    }

    /**
     * Builds a new enemy entity and adds it to the list of enemies to be spawned.
     *
     * @param gameMap The game map.
     * @param enemyName The name of the enemy.
     * @param type The type of the enemy.
     * @param imgPath The image path of the enemy.
     * @param lp The life points of the enemy.
     * @param reward The reward points for defeating the enemy.
     */
    @Override
    public void buildEnemy(final GameMap gameMap, final String enemyName, final String type, final String imgPath,
            final int lp, final int reward) {
        Position2D spawnPosition = gameMap.getSpawnPosition();
        Vector2D direction = gameMap.getPathDirection(spawnPosition);
        Position2D pathEndPosition = gameMap.getPathEndPosition();
        EnemyImpl newEnemy = new EnemyImpl(this.enemies.size(), enemyName, type, imgPath, spawnPosition,
                direction, pathEndPosition, lp, reward);

        this.enemiesToPush.add(newEnemy);
    }

    /**
     * Retrieves the set of currently active enemies.
     *
     * @return The set of currently active enemies.
     */
    @Override
    public Set<Enemy> getCurrentEnemies() {
        return Set.copyOf(this.enemies.stream().filter(e -> e.isAlive()).collect(Collectors.toSet()));
    }

    /**
     * Checks if there are no enemies currently alive and active.
     *
     * @return true if there are no enemies alive, false otherwise.
     */
    private boolean checkIfNoEnemiesAlive() {
        return this.enemies.stream().filter(e -> e.isAlive()).collect(Collectors.toSet()).isEmpty();
    }

    /**
     * Checks if there are no more running enemies in the game.
     *
     * @return true if there are no more running enemies, false otherwise.
     */
    @Override
    public boolean noMoreRunningEnemies() {
        return this.noRunningEnemies;
    }

    /**
     * Sets the game map for managing enemy positions and paths.
     *
     * @param gameMap The game map to set.
     */
    @Override
    public void setMap(final GameMap gameMap) {
        this.gameMap = Optional.of(gameMap);
    }

    /**
     * Retrieves the number of different enemy types available in the game.
     *
     * @return The number of different enemy types.
     */
    @Override
    public int getNEnemyTypes() {
        return this.enemiesConfigFactory.getNEnemyTypes();
    }

    /**
     * Toggles the game pause state by pausing/resuming all enemies.
     */
    @Override
    public void togglePause() {
        if (this.pause) {
            this.enemies.stream().filter(e -> e.getState().equals(EnemyState.PAUSED)).forEach(e -> e.resume());
        } else {
            this.enemies.stream().filter(e -> e.getState().equals(EnemyState.MOVING)).forEach(e -> e.pause());
        }
        this.pause = !this.pause;
    }

    /**
     * Update the state of the game based on the current game state.
     * Update player lives lost, player reward, and check if there are running enemies.
     *
     * @param gameState the current state of the game.
     */
    @Override
    public void update(final GameState gameState) {
        this.playerLivesLost = this.computeNumberOfPlayerLivesLost();
        this.playerReward = this.computePlayerReward();
        this.noRunningEnemies = this.checkIfNoEnemiesAlive();
        this.enemies.addAll(this.enemiesToPush);
        this.enemiesToPush.clear();

        for (Enemy enemy : gameState.getEnemies()) {
            if (enemy.getState().equals(EnemyState.READY)) {
                enemy.startMoving();
            }
            if (!(enemy.getPosition().xInt() == this.gameMap.get().getPathEndPosition().xInt()
                && enemy.getPosition().yInt() == this.gameMap.get().getPathEndPosition().yInt())) {
                enemy.setDirection(this.gameMap.get().getPathDirection(new Position2D(enemy.getPosition().xInt(),
                                   enemy.getPosition().yInt())).scale(ENEMY_SPEED_SCALER));
            }
            enemy.move();
        }
    }
}
