package it.unibo.model.core;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private final Logger logger = LoggerFactory.getLogger(GameEngineImpl.class);
    private static final long FRAME_LIMIT = 20; //minimum time between frames in ms, max 50 per second
    private GameMap map;
    private GameState gameState;
    private final Player player = new PlayerImpl();
    private final DefenseManager defenseManager = new DefenseManagerImpl();
    private final EnemiesManager enemiesManager = new EnemiesManagerImpl();
    private final RoundManagerImpl roudManager = new RoundManagerImpl(enemiesManager);
    private final Set<GameObserver> observers = new HashSet<>();
    private boolean isRunning;
    private boolean isGameOver;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        if (this.map == null) {
            throw new IllegalStateException("No map selected");
        }
        this.isRunning = true;
        this.enemiesManager.setMap(this.map);
        this.defenseManager.setMap(this.map);
        this.registerObserver(defenseManager);
        this.registerObserver(enemiesManager);

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
        this.enemiesManager.togglePause();
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
        long start;
        roudManager.startGame(enemiesManager);
        while (!this.gameState.isGameOver()) {
            try {
                start = System.currentTimeMillis();
                this.updateGameState();
                this.updateObservers();
                this.setDamageAndRewards();
                if (this.gameState.getLastRound()) {
                    return;
                }
                final long delta = System.currentTimeMillis() - start;
                if (delta < FRAME_LIMIT) {
                    Thread.sleep(FRAME_LIMIT - delta);
                }
            } catch (final InterruptedException e) {
                logger.error("Engine interrupted\n", e);
                System.exit(0);
            }
        }
    }

    @Override
    public GameMap getGameMap() {
        return this.map;
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
                final int tmp = player.getRemainingLives();
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

            @Override
            public Set<Enemy> getEnemies() {
                return enemiesManager.getCurrentEnemies();
            }

            @Override
            public int getPLayerReward() {
                return enemiesManager.getPLayerReward();
            }

            @Override
            public int getNumberOfPlayerLivesLost() {
                return enemiesManager.getNumberOfPlayerLivesLost();
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
        player.setMoney(this.gameState.getPLayerReward());
        player.loseLives(this.gameState.getNumberOfPlayerLivesLost());
    }

    private void updateObservers() {
        this.observers.forEach(obs -> obs.update(this.gameState));
    }
}
