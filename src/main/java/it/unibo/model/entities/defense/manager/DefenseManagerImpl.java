package it.unibo.model.entities.defense.manager;

import java.util.Set;

import it.unibo.model.core.GameState;
import it.unibo.model.entities.EntityFactory;
import it.unibo.model.entities.EntityFactoryImpl;
import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.map.GameMap;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;

/**
 * .
 */
public class DefenseManagerImpl implements DefenseManager {

    private Set<Tower> towers = new HashSet<>();
    private Set<Bullet> bullets = new HashSet<>();
    private EntityFactory towerFactory = new EntityFactoryImpl();
	private Optional<GameMap> map;

    /**
     * Costructor.
     */
    public DefenseManagerImpl() { }

    @Override
    public void buildTower(Tower tower) {
        try {
            Tower newTower = towerFactory.loadTower("towers/json/tower" + tower.getId() + ".json");
            newTower.setPosition(tower.getPosition());
            map.get().buildTower(newTower);
            towers.add(newTower);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Override
    public void update(GameState gameState) {
        towers.forEach(tower -> tower.attack(gameState.getEnemies()));
    }

    @Override
    public Set<Tower> getTowers() {
        return Set.copyOf(towers);
    }

    @Override
    public Set<Bullet> getBullets() {
        return Set.copyOf(bullets);
    }

    @Override
    public int getNumberOfTowers() {
        return this.towers.size();
    }

    @Override
	public void setMap(final GameMap gameMap) {
		this.map = Optional.of(gameMap);
	}
}
