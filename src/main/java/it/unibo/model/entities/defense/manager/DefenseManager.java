package it.unibo.model.entities.defense.manager;

import java.util.Set;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.utilities.Position2D;

/**
 * Represents the entity manager to manage all the entities.
 */
public interface DefenseManager {
    /**
     * @param tower tower to be built
     * Builds the entity thread.
    */
    void buildTower(int id, Position2D position2d);

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
}
