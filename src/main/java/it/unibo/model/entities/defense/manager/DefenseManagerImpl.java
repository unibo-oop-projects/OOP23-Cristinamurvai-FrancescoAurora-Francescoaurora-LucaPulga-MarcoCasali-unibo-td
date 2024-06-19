package it.unibo.model.entities.defense.manager;

import java.util.Set;

import it.unibo.model.core.GameObserver;
import it.unibo.model.core.GameState;
import it.unibo.model.entities.EntityFactory;
import it.unibo.model.entities.EntityFactoryImpl;
import it.unibo.model.entities.defense.tower.BasicTower;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.map.GameMap;

import java.io.IOException;
import java.util.HashSet;

/**
 * .
 */
public class DefenseManagerImpl implements DefenseManager, GameObserver {

    private Set<Tower> towers = new HashSet<>();
    EntityFactory towerFactory = new EntityFactoryImpl();
    GameMap map;

    /**
     * Costructor.
     */

    public DefenseManagerImpl() { }

    @Override
    public void buildTower(Tower tower) {
        Tower newTower;
        try {
            newTower = towerFactory.loadTower("towers/json/tower" + tower.getId() + ".json");
            map.buildTower(newTower);
            towers.add(newTower);
        } catch (IOException e) {
            e.printStackTrace();
        } 
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
