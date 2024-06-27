package it.unibo.model.entities.defense.manager;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.model.core.GameState;
import it.unibo.model.entities.EntityFactory;
import it.unibo.model.entities.EntityFactoryImpl;
import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.map.GameMap;

/**
 * .
 */
public class DefenseManagerImpl implements DefenseManager {

    private Set<Tower> towers = new HashSet<>();
    private EntityFactory towerFactory = new EntityFactoryImpl();
    private Optional<GameMap> map;

    /**
     * Costructor.
     */
    public DefenseManagerImpl() {
    }

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
            tower.getBullets().forEach(b -> b.update(gameState));
        });
    }

    @Override
    public Set<Tower> getTowers() {
        return Set.copyOf(towers);
    }

    @Override
    public int getNumberOfTowers() {
        return this.towers.size();
    }

    @Override
    public void setMap(final GameMap gameMap) {
        this.map = Optional.of(gameMap);
    }

    public Set<Bullet> getBullets() {
        return towers.stream()
                .flatMap(tower -> tower.getBullets().stream())
                .collect(Collectors.toSet());
    }

}
