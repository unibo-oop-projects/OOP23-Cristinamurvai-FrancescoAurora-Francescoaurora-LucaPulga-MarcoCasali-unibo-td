package it.unibo.model.core;

import java.util.Set;

import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.map.GameMap;

/**
 * Represents the current state of the game.
 */
public interface GameState {

    /**
     * @return The set of towers currently present
     */
    Set<Tower> getTowers();

    /**
     * @return The set of bullets currently present
     */
    Set<Bullet> getBullets();

    /**
     * @return The current {@link GameMap}
     */
    GameMap getGameMap();

    /**
     * @return The remaining money
     */
    int getMoney();

    /**
     * @return The remaining lives
     */
    int getLives();

    /**
     * @return {@code True} if the game is over {@code False} otherwise
     */
    boolean isGameOver();

    /**
     * @return {@code True} if the game is paused {@code False} if running
     */
    boolean isPaused();

    /**
     * @return The set of enemies currently present TO-DO: remove and find
     * enemies from getEntities
     *
     */
    Set<Enemy> getEnemies();

    /**
     *
     * @return Return time of roud.
     */
    String getRoundTime();

    /**
     * @return round number.
     */
    int getRoundNumber();

    /**
     * @return if is last round.
     */
    boolean getLastRound();

}
