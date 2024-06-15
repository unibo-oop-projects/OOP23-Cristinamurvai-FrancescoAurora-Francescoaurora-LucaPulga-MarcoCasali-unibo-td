package it.unibo.model.entities.defense.manager;

import java.util.Set;

import it.unibo.model.core.GameObserver;
import it.unibo.model.core.GameState;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.map.GameMap;
import it.unibo.model.utilities.Position2D;

import java.util.HashSet;

/**
 * .
 */
public class DefenseManagerImpl implements DefenseManager, GameObserver {

    private Set<Tower> towers = new HashSet<>();
    GameMap map;

    /**
     * Costructor.
     */

    // Gli passo la mappa
    public DefenseManagerImpl() {

    }

    @Override
    public void buildTower(final int towerID, Position2D position2d) {
        // TODO: segnare la cella che la torre occupa quando la costruisco, in modo che non sia più costruibile
        // dall'id mi creo la Tower()
        //registrare la Tower nella mappa

    }

    @Override
    public void update(GameState gameState){
        towers.forEach(tower -> tower.attack(gameState.getEnemies()));
    }

    @Override
    public Set<Tower> getTowers() {
        return Set.copyOf(towers);
    }

    @Override
    public int getNumberOfTowers() {
        return this.towers.size();
    }
}
