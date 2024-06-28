package it.unibo.model.core;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.manager.DefenseManager;
import it.unibo.model.entities.defense.manager.DefenseManagerImpl;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.enemies.EnemiesManager;
import it.unibo.model.entities.enemies.EnemiesManagerImpl;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.map.GameMap;
import it.unibo.model.player.Player;
import it.unibo.model.player.PlayerImpl;
import it.unibo.model.round.RoundManagerImpl;

/**
 * Implementation of {@link GameEngine}.
 */
public final class GameEngineImpl implements GameEngine, Runnable {

    private static final long FRAME_LIMIT = 20; //minimum time between frames in ms, max 50 per second
    private GameMap map = null;
    private GameState gameState = null;
    private final Player player = new PlayerImpl();
    private final DefenseManager defenseManager = new DefenseManagerImpl();
    private final EnemiesManager enemiesManager = new EnemiesManagerImpl();
    private final RoundManagerImpl roudManager = new RoundManagerImpl(enemiesManager);
    private final Set<GameObserver> observers = new HashSet<>();
    private boolean isRunning = false;
    private boolean isGameOver = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        if (this.map == null) {
            throw new IllegalStateException("No map selected");
        }
        this.isRunning = true;
        //added here for enemy test
        this.enemiesManager.setMap(this.map);
        this.defenseManager.setMap(this.map);
        this.registerObserver(defenseManager);

        this.updateGameState();
        final Thread t = new Thread(this);
        t.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void togglePause() {
        this.isRunning = !this.isRunning;
        this.roudManager.togglePause();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameMap(final GameMap map) {
        this.map = map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerObserver(final GameObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Main loop of the game.
     */
    @Override
    public void run() {
        roudManager.startGame(enemiesManager);
        while (!this.gameState.isGameOver()) {
            try {
                long start = System.currentTimeMillis();
                // Temporary added here to test enemies
                this.enemiesManager.updateEnemiesDirections(start);
                this.updateGameState();
                this.updateObservers();
                this.setDamageAndRewards();
                if (this.gameState.getLastRound()) {
                    return;
                }
                long delta = System.currentTimeMillis() - start;
                if (delta < FRAME_LIMIT) {
                    Thread.sleep(FRAME_LIMIT - delta);
                }
            } catch (final InterruptedException e) {
                System.err.println("engine interrupt");
                System.exit(0);
            }
        }
    }

    private void updateGameState() {
        this.gameState = new GameState() {
            @Override
            public Set<Tower> getTowers() {
                return defenseManager.getTowers();
            }

            @Override
            public int getMoney() {
                return player.getMoney();
            }

            @Override
            public int getLives() {
                int tmp = player.getRemainingLives();
                if (tmp <= 0) {
                    isGameOver = true;
                }
                return tmp;
            }

            @Override
            public boolean isGameOver() {
                return isGameOver;
            }

            @Override
            public GameMap getGameMap() {
                return map;
            }

            @Override
            public boolean isPaused() {
                return !isRunning;
            }

            //added here for enemy test
            @Override
            public Set<Enemy> getEnemies() {
                return enemiesManager.getCurrentEnemies();
            }

            @Override
            public String getRoundTime() {
                return roudManager.getTime();
            }

            @Override
            public int getRoundNumber() {
                return roudManager.getRound();
            }

            @Override
            public boolean getLastRound() {
                return roudManager.getLastRound();
            }

            @Override
            public Set<Bullet> getBullets() {
                return defenseManager.getBullets();
            }
        };
    }

    private void setDamageAndRewards() {
        List<Integer> damageAndRewards = enemiesManager.getDamageAndRewardsFromFinishedEnemies();
        player.setMoney(damageAndRewards.get(1));
        player.loseLives(damageAndRewards.get(0));
    }

    private void updateObservers() {
        this.observers.forEach(obs -> obs.update(this.gameState));
    }

    @Override
    public void buildTower(final Tower tower) {
        if (player.getMoney() >= tower.getCost()) {
            defenseManager.buildTower(tower);
            player.setMoney(-tower.getCost());
        } else {
            tower.setPosition(null);
        }
    }
}
