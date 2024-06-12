package it.unibo.model.entities.defense.manager;

import java.util.List;
import java.util.Set;

import it.unibo.model.entities.defense.tower.Tower;

/**
 * Represents the entity manager to manage all the entities.
 */
public interface DefenseManager {
    /**
     * @param tower tower to be built
     * Builds the entity thread.
    */
    void buildTower(Tower tower);

    /**
     * Represents the towers.
     * @return a set of all the active towers.
    */
    Set<Tower> getTowers();

    /**
     * Represents the towers.
     * @return a set of all the active towers.
    */
    int getNumberOfTowers();

    /**
     * Activate all towers.
     * @param towers
     */
    void activateAllTowers(List<Tower> towers);

    /**
     * Stop all threads dedicated to towers.
     */
    void stopAllTowers();
}
