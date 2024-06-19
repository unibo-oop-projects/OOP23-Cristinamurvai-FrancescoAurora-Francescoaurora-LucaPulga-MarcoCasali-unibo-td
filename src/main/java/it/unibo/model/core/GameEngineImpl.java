package it.unibo.model.core;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import it.unibo.model.entities.Entity;
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
import it.unibo.model.utilities.Position2D;

/**
 * Implementation of {@link GameEngine}.
 */
public class GameEngineImpl implements GameEngine, Runnable {
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
        //this.enemiesManager.parseEnemies();

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
    public void run() {
        roudManager.startGame();
        while (!this.gameState.isGameOver()) {
            try {
                long start = System.currentTimeMillis();
                // Temporary added here to test enemies
                this.enemiesManager.updateEnemiesDirections(start);
                this.updateGameState();
                this.updateObservers();
                if (this.gameState.getLastRound() == true){                   
                    return;
                }
                long delta = System.currentTimeMillis() - start;
                if (delta < FRAME_LIMIT) {
                    Thread.sleep(FRAME_LIMIT - delta);
                }
            } catch (Exception e) {
                System.err.println("engine interrupt");
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    private void updateGameState() {
        this.gameState = new GameState() {
            @Override
            public Set<Entity> getEntities() {
                return Stream.concat(enemiesManager.getCurrentEnemies().stream(),
                 defenseManager.getTowers().stream()).collect(Collectors.toSet());
            }

            @Override
            public int getMoney() {
                return player.getMoney();
            }

            @Override
            public int getLives() {
                return player.getRemainingLives();
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
            public boolean getLastRound(){
                return roudManager.getLastRound();
            }
        };
    }

    private void updateObservers() {
        this.observers.forEach(obs -> obs.update(this.gameState));
    }

    @Override
    public void buildTower(Tower tower) {
        defenseManager.buildTower(tower);
    }
}
