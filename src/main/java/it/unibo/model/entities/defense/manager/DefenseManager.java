package it.unibo.model.entities.defense.manager;

import java.util.Set;

import it.unibo.model.core.GameObserver;
import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.map.GameMap;
import it.unibo.model.player.Player;

/**
 * Represents the defense manager to manage all the defense entities such as {@link Tower} and {@link Bullet} .
 */
public interface DefenseManager extends GameObserver {

    /**
     * Selected @param tower to be built, chosen by the {@link Player}.
     */
    void buildTower(Tower tower);

    /**
     * Represents the towers.
     * @return a set of all the active towers.
     */
    Set<Tower> getTowers();

    /**
     * Represents the number of towers.
     * @return the number of all the active towers.
     */
    int getNumberOfTowers();

    /**
     * Set the actual map.
     * @param gameMap actual {@link GameMap}
     */
    void setMap(GameMap gameMap);

    /**
     * Represents the Bullets fired by a specific {@link Tower}.
     * @return a set of all the active bullets fired by a {@link Tower}.
     */
    Set<Bullet> getBullets();
}
