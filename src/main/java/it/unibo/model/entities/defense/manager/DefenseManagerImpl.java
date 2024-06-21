package it.unibo.model.entities.defense.manager;

import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.model.core.GameState;
import it.unibo.model.entities.EntityFactory;
import it.unibo.model.entities.EntityFactoryImpl;
import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.map.GameMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * .
 */
public class DefenseManagerImpl implements DefenseManager {

    private Set<Tower> towers = new HashSet<>();
    private EntityFactory towerFactory = new EntityFactoryImpl();
	private Optional<GameMap> map;
    private Set<Bullet> bullets = new HashSet<>();

    /**
     * Costructor.
     */
    public DefenseManagerImpl() { }

    @Override
    public void buildTower(Tower tower) {
        try {
            Tower newTower = towerFactory.loadTower("towers/json/tower" + tower.getId() + ".json");
            newTower.setPosition(tower.getPosition());
            map.ifPresent(gameMap -> gameMap.buildTower(newTower));
            towers.add(newTower);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Override
    public void update(GameState gameState) {
        towers.forEach(tower -> {
            tower.attack(gameState.getEnemies());
            if (!tower.getBullets().isEmpty()) {
                bullets.addAll(tower.getBullets());
                tower.clearBullets();  // Clear bullets from tower after collecting them
            }
        });
        updateBullets(gameState);
    }

    @Override
    public Set<Tower> getTowers() {
        return Set.copyOf(towers);
    }

    @Override
    public Set<Bullet> getBullets() {
        return towers.stream()
                    .flatMap(tower -> tower.getBullets().stream())
                    .collect(Collectors.toSet());
    }
    @Override
    public int getNumberOfTowers() {
        return this.towers.size();
    }

    @Override
	public void setMap(final GameMap gameMap) {
		this.map = Optional.of(gameMap);
	}

    private void updateBullets(GameState gameState) {
        Set<Bullet> bulletsToRemove = new HashSet<>();
        bullets.forEach(bullet -> {
            bullet.update(gameState);
            if (bullet.hasReachedTarget()) {
                bulletsToRemove.add(bullet);
            }
        });
        bullets.removeAll(bulletsToRemove);
    }
}
